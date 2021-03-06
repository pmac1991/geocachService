package geoservice



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured


@Secured('isAuthenticated()')
@Transactional(readOnly = true)
class UserController {

	//static scaffold = true

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def springSecurityService

	@Secured('permitAll')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
    }
	
	@Secured('permitAll')
    def show(User userInstance) {
        respond userInstance
    }

	@Secured('permitAll')
    def create() {
        respond new User(params)
    }

	@Secured('permitAll')
    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'create'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userInstance.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

	@Secured('isAuthenticated()')
    def edit(User userInstance) {
		def currUser = springSecurityService.currentUser
	
		if(userInstance != currUser){
			request.withFormat {
				form {
					flash.message = "You are not authorized to change this item"
					redirect action: "index", method: "GET"
				}
				'*'{ render status: NOT_FOUND }
			}
			return
		}else{
		        respond userInstance
		}
    }

    @Transactional
    def update(User userInstance) {
	
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'edit'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*'{ respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

		def currUser = springSecurityService.currentUser
	
		if(userInstance != currUser){
			request.withFormat {
				form {
					flash.message = "You are not authorized to delete this item"
					redirect action: "index", method: "GET"
				}
				'*'{ render status: NOT_FOUND }
			}
			return
		}
	
        if (userInstance == null) {
            notFound()
            return
        }
		
		Collection<Geocatch> userGeocathces = Geocatch.findAllByAuthor(userInstance);
		
		userGeocathces*.delete flush:true

        userInstance.delete flush:true
		
		redirect(controller: "logout")
		
		return

        /*request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }*/
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userInstance.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
