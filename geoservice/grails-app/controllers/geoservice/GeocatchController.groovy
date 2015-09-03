package geoservice

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
@Transactional(readOnly = true)
class GeocatchController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def geocoderService
	
	def springSecurityService
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Geocatch.list(params), model:[geocatchInstanceCount: Geocatch.count()]
    }

    def show(Geocatch geocatchInstance) {
        respond geocatchInstance
    }

    def create() {
        respond new Geocatch(params)
    }

    @Transactional
    def save(Geocatch geocatchInstance) {
        if (geocatchInstance == null) {
            notFound()
            return
        }

        if (geocatchInstance.hasErrors()) {
            respond geocatchInstance.errors, view:'create'
            return
        }
		
		def point =  geocoderService.findAddress(geocatchInstance.address)
		
		geocatchInstance.lat = point.lat
		
		geocatchInstance.lon = point.lng
		
		def user = springSecurityService.currentUser
		
		geocatchInstance.author = user

        geocatchInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'geocatchInstance.label', default: 'Geocatch'), geocatchInstance.id])
                redirect geocatchInstance
            }
            '*' { respond geocatchInstance, [status: CREATED] }
        }
    }

    def edit(Geocatch geocatchInstance) {
        respond geocatchInstance
    }

    @Transactional
    def update(Geocatch geocatchInstance) {
        if (geocatchInstance == null) {
            notFound()
            return
        }

        if (geocatchInstance.hasErrors()) {
            respond geocatchInstance.errors, view:'edit'
            return
        }

        geocatchInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Geocatch.label', default: 'Geocatch'), geocatchInstance.id])
                redirect geocatchInstance
            }
            '*'{ respond geocatchInstance, [status: OK] }
        }
    }
	
	@Transactional
	def markAsVisited(Geocatch geocatchInstance){
		def currUser = springSecurityService.currentUser
	
		geocatchInstance.addToVisitors(currUser)
		geocatchInstance.save() //flush:true
		
		render geocatchInstance.visitors
		
		//user.addToVisitedPlaces(geocatchInstance)
		
		//user.save flush:true
	}

    @Transactional
    def delete(Geocatch geocatchInstance) {

        if (geocatchInstance == null) {
            notFound()
            return
        }

        geocatchInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Geocatch.label', default: 'Geocatch'), geocatchInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'geocatchInstance.label', default: 'Geocatch'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
