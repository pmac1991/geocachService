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
}
