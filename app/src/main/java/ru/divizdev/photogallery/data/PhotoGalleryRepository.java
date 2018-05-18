package ru.divizdev.photogallery.data;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.divizdev.photogallery.API.IPixabayAPI;
import ru.divizdev.photogallery.API.PixabyResponse;
import ru.divizdev.photogallery.BuildConfig;
import ru.divizdev.photogallery.entities.ImageCategory;
import ru.divizdev.photogallery.entities.ImageUI;
import ru.divizdev.photogallery.entities.TypeErrorLoad;

public class PhotoGalleryRepository implements IPhotoGalleryRepository {

    private static final String IMAGE_TYPE_DEFAULT = "photo";
    private static final int TOP_DEFAULT = 200;
    private static final String ORDER = "latest";
    private Map<Integer, ImageUI> _imageUIMap;

    @Override
    public void loadListImages(@NonNull final ICallBackListImages callBack) {
        loadListImages(callBack, false);
    }

    @Override
    public void loadListImages(@NonNull final ICallBackListImages callBack, Boolean isRefresh) {

        if (!isRefresh && _imageUIMap != null && _imageUIMap.size() > 0) {
            callBack.onImages(new ArrayList<>(_imageUIMap.values()));
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        IPixabayAPI pixabayAPI = retrofit.create(IPixabayAPI.class);
        Call<PixabyResponse> data = pixabayAPI.getData(BuildConfig.API_KEY, IMAGE_TYPE_DEFAULT, ImageCategory.animals.name(), ORDER, TOP_DEFAULT, true);
        data.enqueue(new Callback<PixabyResponse>() {
            @Override
            public void onResponse(@NonNull Call<PixabyResponse> call, @NonNull Response<PixabyResponse> response) {

                if (response.body() != null && response.body().getImages() != null) {
                    _imageUIMap = ImageUI.convertToMap(response.body().getImages());
                    callBack.onImages(new ArrayList<>(_imageUIMap.values()));
                } else {

                    callBack.onError(TypeErrorLoad.NoBody, "");
                }
            }

            @Override
            public void onFailure(@NonNull Call<PixabyResponse> call, @NonNull Throwable throwable) {
                Log.e("r", throwable.getMessage());
                callBack.onError(TypeErrorLoad.BadConnect, throwable.getMessage());

            }
        });
    }

    @Nullable
    public ImageUI getImageUI(Integer id) {
        return _imageUIMap.get(id);
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, null, new SecureRandom());
            TLSSocketFactory tlsSocketFactory = new TLSSocketFactory(sc);
            // Workaround for Android 7.0 TLS bug
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
                String[] suites = tlsSocketFactory.getDefaultCipherSuites();
                ConnectionSpec tlsSpec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).cipherSuites(suites).build();
                clientBuilder.connectionSpecs(Arrays.asList(tlsSpec, ConnectionSpec.CLEARTEXT));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return  clientBuilder.build();
    }


    private class TLSSocketFactory extends SSLSocketFactory {

        private final javax.net.ssl.SSLSocketFactory socketFactory;

        TLSSocketFactory(SSLContext sslContext) {
            super();
            this.socketFactory = sslContext.getSocketFactory();
        }

        @Override
        public Socket createSocket(
                final Socket socket,
                final String host,
                final int port,
                final boolean autoClose
        ) throws java.io.IOException {

            SSLSocket sslSocket = (SSLSocket) this.socketFactory.createSocket(
                    socket,
                    host,
                    port,
                    autoClose
            );

            //Enable all supported Protocols
            sslSocket.setEnabledProtocols(sslSocket.getSupportedProtocols());

            return sslSocket;
        }

        @Override
        public String[] getDefaultCipherSuites() {
            return this.socketFactory.getDefaultCipherSuites();
        }

        @Override
        public String[] getSupportedCipherSuites() {
            return this.socketFactory.getSupportedCipherSuites();
        }

        //NoTLS
        @Override
        public Socket createSocket(String s, int i) throws IOException {
            return null;
        }
        @Override
        public Socket createSocket(String s, int i, InetAddress inetAddress, int i2) throws IOException {
            return null;
        }
        @Override
        public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            return null;
        }
        @Override
        public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
            return null;
        }


    }


}


