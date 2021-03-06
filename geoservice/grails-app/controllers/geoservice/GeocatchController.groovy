package geoservice

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
@Transactional(readOnly = true)
class GeocatchController {

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

	def geocoderService
	
	def springSecurityService
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Geocatch.list(params), model:[geocatchInstanceCount: Geocatch.count()]
    }

    def show(Geocatch geocatchInstance) {
		def currUser = springSecurityService.currentUser
	
        //respond geocatchInstance, currUser
		[geocatchInstance: geocatchInstance, currUser: currUser]
    }
	
	def showGeocachCreatedByCurrUser() {
		def currUser = springSecurityService.currentUser
	
		def result = Geocatch.findAllByAuthor(currUser)
	
		respond result, model:[geocatchInstanceCount: Geocatch.countByAuthor(currUser)], view:'index'
    }
	
	def showGeocachVisitedByCurrUser() {
		def currUser = springSecurityService.currentUser
	
		def result = Geocatch.findAll()

		def finalResult = []
	
		for(Geocatch g: result ){
			if(g.visitors.contains(currUser)){
			
			finalResult.add(g)
			
			}
		}
	
		respond finalResult, model:[geocatchInstanceCount: finalResult.size()], view:'index'
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
		
		if(point != null){
		
			geocatchInstance.lat = point.lat
		
			geocatchInstance.lon = point.lng
		
		}

		def user = springSecurityService.currentUser
		
		geocatchInstance.author = user;
		
        geocatchInstance.save flush:true

        flash.message = message(code: 'default.created.message', args: [message(code: 'geocatchInstance.label', default: 'Geocatch'), geocatchInstance.id])
		redirect action: "index", method: "GET"

    }

    def edit(Geocatch geocatchInstance) {
	
		def currUser = springSecurityService.currentUser
	
		if(geocatchInstance.author != currUser){
			request.withFormat {
				form {
					flash.message = "You are not authorized to change this item"
					redirect action: "index", method: "GET"
				}
				'*'{ render status: NOT_FOUND }
			}
			return
		}else{
		        respond geocatchInstance
		}
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

        flash.message = message(code: 'default.updated.message', args: [message(code: 'Geocatch.label', default: 'Geocatch'), geocatchInstance.id])
		redirect action: "index", method: "GET"

    }
	
	@Transactional
	def markAsVisited(Geocatch geocatchInstance){
		def currUser = springSecurityService.currentUser
	
		if(geocatchInstance.visitors.id.contains(currUser)){
			flash.message = "you already visited this place!"
			redirect action: "index", method: "GET"
		}else{
			geocatchInstance.addToVisitors(currUser)
			geocatchInstance.save flush:true
		
			flash.message = "Operation succesful!"
			redirect action: "index", method: "GET"
		}

		
	}
	
	def showPhoto() {
		//response.contentType = "image/jpeg"
		//response.contentLength = geocatchInstance?.picture.length
		//response.outputStream.write(geocatchInstance?.picture)
		def geocatchInstance = Geocatch.get(params.id)
		response.outputStream << geocatchInstance.picture // write the image to the outputstream
		response.outputStream.flush()
		
	}

    @Transactional
    def delete(Geocatch geocatchInstance) {

		def currUser = springSecurityService.currentUser	
	
		if(geocatchInstance.author != currUser){
			request.withFormat {
				form {
					flash.message = "You are not authorized to delete this item"
					redirect action: "index", method: "GET"
				}
				'*'{ render status: NOT_FOUND }
			}
			return
		}
	
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
