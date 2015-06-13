import au.com.directoryservice.Category
import au.com.directoryservice.Day
import au.com.directoryservice.Location
import au.com.directoryservice.ServiceProvider
import au.com.directoryservice.HoursOfOperation
import au.com.directoryservice.ServiceProvider
import grails.converters.JSON
import org.joda.time.LocalTime

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller(Day) {
            return it.toString()
        }

        JSON.registerObjectMarshaller(HoursOfOperation) {
            return [
                    "id"       : it.id,
                    "days"     : it.days,
                    "startTime": it.startTime.toString("HH:mm:ss'Z'"),
                    "endTime"  : it.endTime.toString("HH:mm:ss'Z'")
            ]
        }

        JSON.registerObjectMarshaller(Category) {
            return [
                    "id"      : it.id,
                    "name"    : it.name,
                    "iconPath": it.iconPath,
                    "keywords": it.keywords
            ]
        }

        JSON.registerObjectMarshaller(Location) {
            return [
                    "id"          : it.id,
                    "name"        : it.name,
                    "shopNumber"  : it.shopNumber,
                    "streetNumber": it.streetNumber,
                    "streetName"  : it.streetName,
                    "suburb"      : it.suburb,
                    "postcode"    : it.postcode,
                    "latitude"    : it.latitude,
                    "longitude"   : it.longitude,
                    "website"     : it.website
            ]
        }

        JSON.registerObjectMarshaller(ServiceProvider) {
            return [
                    "id"         : it.id,
                    "name"       : it.name,
                    "category"   : it.category,
                    "description": it.description,
                    "imagePath"  : it.imagePath,
                    "website"    : it.website
            ]
        }
        
        environments {
            development {

                // Inject Test Data

                Category medical = Category.build(name: "Medical")
                Category sportsGroups = Category.build(name: "Sports Groups")
                Category aidServices = Category.build(name: "Aid Services")
                Category educationCentres = Category.build(name: "Education Centres")

                Location townSquare = Location.build(name: "Town Square", streetName: "Chris Murphy Walk Path",
                        suburb: "Mount Druitt", postcode: "2770",
                        latitude: "-33.767606", longitude: "150.821143")

                Location educationCentre = Location.build(name: "The Aengus Kavanagh Education and Equity Centre",
                        streetNumber: "59", streetName: "Railway Street",
                        suburb: "Mount Druitt", postcode: "2770",
                        latitude: "-33.767714", longitude: "150.830227")

                Location medicalServicesSydney = Location.build(name: "Aboriginal Medical Services Western Sydney",
                        streetNumber: "2", streetName: "Palmerston Rd (Mount Druitt Village)",
                        suburb: "Mount Druitt", postcode: "2770",
                        latitude: "-33.769582", longitude: "150.811787")

                Location communityHall = Location.build(name: "Mount Druitt Community Hall",
                        streetNumber: "85 - 87", streetName: "Mount Druitt Rd",
                        suburb: "Mount Druitt", postcode: "2770",
                        latitude: "-33.773077", longitude: "150.810517")

                HoursOfOperation daily = HoursOfOperation.build(days: Day.values(), startTime: new LocalTime(9, 0), endTime: new LocalTime(17, 0))
                HoursOfOperation tuesdayAfternoon = HoursOfOperation.build(days: [Day.Tuesday], startTime: new LocalTime(14, 0), endTime: new LocalTime(16, 0))
                HoursOfOperation fridayEvening = HoursOfOperation.build(days: [Day.Friday], startTime: new LocalTime(18, 0), endTime: new LocalTime(20, 0))
                HoursOfOperation mondayAndWednesdayEvenings = HoursOfOperation.build(days: [Day.Monday, Day.Wednesday], startTime: new LocalTime(18, 0), endTime: new LocalTime(20, 0))

                ServiceProvider.build(name: "Language Class", category: educationCentres,
                        shortDescription: "Learn how to English", disclaimer: "Please be there 15 minutes prior to class",
                        location: educationCentre, times: [mondayAndWednesdayEvenings, fridayEvening])

                ServiceProvider.build(name: "Free Medical Assistance", category: medical,
                        shortDescription: "We're here to help you", longDescription: "Free medical advice and services for those in need",
                        location: medicalServicesSydney, times: [daily])

                ServiceProvider.build(name: "Hopskotch!", category: sportsGroups,
                        shortDescription: "Come and make some new friends",
                        location: townSquare, times: [tuesdayAfternoon])

                ServiceProvider.build(name: "Community Discussion", category: aidServices,
                        shortDescription: "Weekly discussion on aid services",
                        longDescription: "We'll discuss and plan for the addition of new aid services in Mount Druitt",
                        location: communityHall, times: [fridayEvening])
            }
        }
    }

    def destroy = {
    }
}
