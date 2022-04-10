package edu.miu.cs473de.cv_builder.models

class ContactModel {
    var contactIcon:Int?=0
    var contactType:ContactType?=null
    var contactInfo:String?=null
    var contactTitle:String?=null

    init {
        when(contactType){
            ContactType.MOBILE-> contactTitle="Mobile"
            ContactType.EMAIL-> contactTitle="Email"
            ContactType.LINKEDIN-> contactTitle="LinkedIn Website"
            ContactType.GITHUB-> contactTitle="GitHub"
            ContactType.RESUME-> contactTitle="Resume.pdf"
        }
    }
}