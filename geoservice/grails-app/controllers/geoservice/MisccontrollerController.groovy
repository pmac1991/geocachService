package geoservice

import grails.plugin.springsecurity.annotation.Secured



@Secured('permitAll')
class MisccontrollerController {

	def springSecurityService

	@Secured('permitAll')
    def index() {
		def currUser = springSecurityService.currentUser
		
		redirect controller: "user", action: "show", id: currUser.id
	}
	
	def showUsersGeocach(Integer max){
		params.max = Math.min(max ?: 10, 100)
        respond Geocatch.list(params), model:[geocatchInstanceCount: Geocatch.count()]
	}
}
