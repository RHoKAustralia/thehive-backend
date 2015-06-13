import au.com.directoryservice.App
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

                App theHive = App.build(name: "The Hive")
                App iDiversity = App.build(name: "iDiversity")

                Category medical = Category.build(app: theHive, name: "Medical", keywords: ["Health", "Medical", "Sick"])
                Category sportsGroups = Category.build(app: theHive, name: "Sports Groups", keywords: ["Sports", "Sport", "Footy"])
                Category aidServices = Category.build(app: theHive, name: "Aid Services", keywords: ["Sports", "Sport", "Footy"])
                Category educationCentres = Category.build(app: theHive, name: "Education Centres", keywords: ["Learning", "Education"])

                ServiceProvider languageClass = ServiceProvider.build(app: theHive, name: "Language Class", category: educationCentres,
                        description: "Learn how to English")

                ServiceProvider medicalAssistance = ServiceProvider.build(app: theHive, name: "Free Medical Assistance", category: medical,
                        description: "We're here to help you")

                ServiceProvider hopskotch = ServiceProvider.build(app: theHive, name: "Hopskotch!", category: sportsGroups,
                        description: "Come and make some new friends")

                ServiceProvider communityDiscussion = ServiceProvider.build(app: theHive, name: "Community Discussion", category: aidServices,
                        description: "Weekly discussion on aid services")

                Location townSquare = Location.build(app: theHive, serviceProvider: hopskotch, name: "Town Square", streetName: "Chris Murphy Walk Path",
                        suburb: "Mount Druitt", postcode: "2770",
                        latitude: "-33.767606", longitude: "150.821143")

                Location educationCentre = Location.build(app: theHive, serviceProvider: languageClass, name: "The Aengus Kavanagh Education and Equity Centre",
                        streetNumber: "59", streetName: "Railway Street",
                        suburb: "Mount Druitt", postcode: "2770",
                        latitude: "-33.767714", longitude: "150.830227")

                Location medicalServicesSydney = Location.build(app: theHive, serviceProvider: medicalAssistance, name: "Aboriginal Medical Services Western Sydney",
                        streetNumber: "2", streetName: "Palmerston Rd (Mount Druitt Village)",
                        suburb: "Mount Druitt", postcode: "2770",
                        latitude: "-33.769582", longitude: "150.811787")

                Location communityHall = Location.build(app: theHive, serviceProvider: communityDiscussion, name: "Mount Druitt Community Hall",
                        streetNumber: "85 - 87", streetName: "Mount Druitt Rd",
                        suburb: "Mount Druitt", postcode: "2770",
                        latitude: "-33.773077", longitude: "150.810517")

                HoursOfOperation daily = HoursOfOperation.build(app: theHive, location: medicalServicesSydney,
                        days: Day.values(), startTime: new LocalTime(9, 0), endTime: new LocalTime(17, 0))

                HoursOfOperation tuesdayAfternoon = HoursOfOperation.build(app: theHive, location: townSquare,
                        days: [Day.Tuesday], startTime: new LocalTime(14, 0), endTime: new LocalTime(16, 0))

                HoursOfOperation fridayEvening = HoursOfOperation.build(app: theHive, location: educationCentre,
                        days: [Day.Friday], startTime: new LocalTime(18, 0), endTime: new LocalTime(20, 0))

                HoursOfOperation mondayAndWednesdayEvenings = HoursOfOperation.build(app: theHive, location: communityHall,
                        days: [Day.Monday, Day.Wednesday], startTime: new LocalTime(18, 0), endTime: new LocalTime(20, 0))
            }
        }
    }

    def destroy = {
    }
}
