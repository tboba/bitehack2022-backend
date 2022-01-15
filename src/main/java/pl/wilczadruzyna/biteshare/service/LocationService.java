package pl.wilczadruzyna.biteshare.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LocationService {

    private final GeocodeService geocodeService;

    public LocationService(GeocodeService geocodeService) {
        this.geocodeService = geocodeService;
    }

    public String findExactCityFrom(double latitude, double longitude) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = geocodeService.getVendor();
        GeocodingResult[] result = GeocodingApi.reverseGeocode(context, new LatLng(latitude, longitude))
                .resultType(AddressType.LOCALITY).awaitIgnoreError();

        String receivedAddress = result[0].formattedAddress;
        return receivedAddress.split(",")[0];
    }

}
