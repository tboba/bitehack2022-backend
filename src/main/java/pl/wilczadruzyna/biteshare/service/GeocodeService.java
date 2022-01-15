package pl.wilczadruzyna.biteshare.service;

import com.google.maps.GeoApiContext;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class GeocodeService {

    private GeoApiContext context;

    public GeocodeService() {
        initializeVendor();
    }

    public GeoApiContext getVendor() {
        return context;
    }

    private void initializeVendor() {
        String googleApiKey = System.getenv("bitehack-googlekey");
        context = new GeoApiContext.Builder()
                .apiKey(googleApiKey)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

}
