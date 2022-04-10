package edu.miu.cs473de.cv_builder.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
class User:Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id: Long=0
    @ColumnInfo(name = "first_name")
    var fName:String?=""
    @ColumnInfo(name = "last_name")
    var lName:String?=""
    @ColumnInfo(name = "email")
    var email:String?=""
    @ColumnInfo(name = "password")
    var password:String?=""
    @ColumnInfo(name = "img_url")
    var image_url:Int?=0
    @ColumnInfo(name = "job_title")
    var jobTitle:String?=""
    @ColumnInfo(name = "career_note")
    var careerNote:String?=""
    @ColumnInfo(name = "work_experience")
    var workExperience:Map<String,List<String>>?=HashMap()
    @ColumnInfo(name = "work_experience_list")
    var workExperienceList:List<Experience>?=ArrayList()
    @ColumnInfo(name = "education_list")
    var educationList:List<Education>?=ArrayList()
    @ColumnInfo(name = "certification_list")
    var certificationList:List<Certification>?=ArrayList()

    //Contacts
    @ColumnInfo(name = "phone")
    var phone:String?=""
    @ColumnInfo(name = "linkedIn")
    var linkedIn:String?=""
    @ColumnInfo(name = "github")
    var github:String?=""
    @ColumnInfo(name = "pdf")
    var pdf:String?=""
    @ColumnInfo(name = "telegram")
    var telegram:String?=""
    @ColumnInfo(name = "whatsapp")
    var whatsapp:String?=""

    //aboutMe
    @ColumnInfo(name = "about_me")
    var aboutMe:String?=""


    constructor()
    constructor(fName: String?, lName: String?, email: String?, password: String?) {
        this.fName = fName
        this.lName = lName
        this.email = email
        this.password = password
    }

}


