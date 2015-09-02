package geoservice



import grails.test.mixin.*
import spock.lang.*

@TestFor(GeocatchController)
@Mock(Geocatch)
class GeocatchControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.geocatchInstanceList
            model.geocatchInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.geocatchInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def geocatch = new Geocatch()
            geocatch.validate()
            controller.save(geocatch)

        then:"The create view is rendered again with the correct model"
            model.geocatchInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            geocatch = new Geocatch(params)

            controller.save(geocatch)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/geocatch/show/1'
            controller.flash.message != null
            Geocatch.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def geocatch = new Geocatch(params)
            controller.show(geocatch)

        then:"A model is populated containing the domain instance"
            model.geocatchInstance == geocatch
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def geocatch = new Geocatch(params)
            controller.edit(geocatch)

        then:"A model is populated containing the domain instance"
            model.geocatchInstance == geocatch
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            status == 404

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def geocatch = new Geocatch()
            geocatch.validate()
            controller.update(geocatch)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.geocatchInstance == geocatch

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            geocatch = new Geocatch(params).save(flush: true)
            controller.update(geocatch)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/geocatch/show/$geocatch.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            status == 404

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def geocatch = new Geocatch(params).save(flush: true)

        then:"It exists"
            Geocatch.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(geocatch)

        then:"The instance is deleted"
            Geocatch.count() == 0
            response.redirectedUrl == '/geocatch/index'
            flash.message != null
    }
}
