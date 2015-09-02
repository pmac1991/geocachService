package geoservice

import grails.transaction.Transactional

@Transactional
class GeocoderService {

	GeocoderService geocoderService

    def findAddress(String address) {
		 def result = null
        withHttp(uri: "http://maps.googleapis.com") {
            def html = get(path : '/maps/api/geocode/json', query : [address:address])
            if ( html.results != null && html.results.size() > 0 ) {
                def partial = html.results[0]
                if ( partial.geometry != null && partial.geometry.location != null) {
                    def lat = partial.geometry.location.lat
                    def lng = partial.geometry.location.lng
                    result = [lat:Double.valueOf(lat.toString()), lng:Double.valueOf(lng.toString())]
                }
            }
        }
        return result

    }
}
