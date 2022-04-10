package edu.miu.cs473de.cv_builder.ui

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import edu.miu.cs473de.cv_builder.R
import edu.miu.cs473de.cv_builder.common.CommonConstrains.MSharedPreference
import edu.miu.cs473de.cv_builder.common.CommonConstrains.USER_REPO
import edu.miu.cs473de.cv_builder.common.Constrains
import edu.miu.cs473de.cv_builder.models.Certification
import edu.miu.cs473de.cv_builder.models.Education
import edu.miu.cs473de.cv_builder.models.Experience
import edu.miu.cs473de.cv_builder.models.User
import edu.miu.cs473de.cv_builder.ui.adapters.ViewPagerAdapter
import kotlinx.coroutines.*

class HomeActivity : AppCompatActivity() {
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var tab_toolbar = findViewById<Toolbar>(R.id.toolbar)
        var tab_viewpager = findViewById<ViewPager>(R.id.tab_viewpager)
        var tab_tablayout = findViewById<TabLayout>(R.id.tab_tablayout)

        setSupportActionBar(tab_toolbar)
        getUserData()
        tab_tablayout.setupWithViewPager(tab_viewpager)
        CoroutineScope(Dispatchers.IO)
            .launch {
                getUserName()
            }
        setupViewPager(tab_viewpager)


    }

    private fun getUserData() {
        if (MSharedPreference?.contains("userData") == true)
            this.user = getUserDataFromSP()
        else
            this.user = getUserDataFromRunTime()
    }

    private fun getUserDataFromRunTime(): User {
        val user = User()
        user.fName = "Mickey"
        user.lName = "ABURJEILA"
        user.jobTitle = "Software Engineer"
        user.image_url = R.drawable.nickey_mouse_reading
        user.careerNote = "Completed on-compus studies and currently taking distance education " +
                "courses to complete a Master's Degree in Computer Science (Available for full-time, W2 employment)."
        val workMap = HashMap<String, List<String>>()
        workMap[Constrains.languages] = arrayListOf("Java", "Java Script", "PL/SQL")
        workMap[Constrains.frameworks_Web] =
            arrayListOf("Spring(Boot, MVC, Security)", "Hibernates", "JDBC")
        workMap[Constrains.Microservices_cloud] =
            arrayListOf("AWS", "GCP", "Docker", "Kubernetes", "Kafka")
        workMap[Constrains.databases] =
            arrayListOf("Oracle PL/SQL", "MySQL", "MangoDB")
        workMap[Constrains.tools] =
            arrayListOf("InteliJ IDEA", "Maven", "GitHub", "GitLab", "UML")
        user.workExperience = workMap

        //About Me
        user.aboutMe =
            "Enthusiastic software developer with more than 5 years of hands-on and professional " +
                    "experience in end-to-end design and development of software solutions, using " +
                    "all phases of the software development lifecycle.  Skills cover a variety " +
                    "of languages \u200B\u200Band technologies such as Java, Angular 2+, Spring " +
                    "Framework, and Oracle PL/SQL.  Master's and Bachelor's degrees in Mathematics " +
                    "and Computer Science"
        val educationList=ArrayList<Education>()
        val certificationList=ArrayList<Certification>()
        educationList.add(
            Education(1,R.drawable.miu_logo,
            "Maharishi International University",
        "Master of Science in Computer Science")
        )
        educationList.add(
            Education(2,R.drawable.massachusetts_institute_of_technology_logo,
                "Massachusetts institute of technology",
                "Bachelor of Science in Computer Science")
        )
        user.educationList=educationList
        certificationList.add(
            Certification(1,R.drawable.oca_cer_logo,
                "OCA Java SE 8 Programmer I",2020
            )
        )
        certificationList.add(
            Certification(2,R.drawable.mcf_cer_logo,
            "Microsoft Certified Fundamentals",2021)
        )
        user.certificationList=certificationList

        val workExperienceList=ArrayList<Experience>()
        workExperienceList.add(
            Experience(
                1,R.drawable.google_logo,
                "Software Developer",
                "Google" ,
                "Apr 2020 - Present",
                "LA, USA",
                "Developing flutter apps"
            )
        )
        workExperienceList.add(
            Experience(
                1,R.drawable.oca_cer_logo,
                "Database developer",
                "ORACLE" ,
                "Jul 2018 - Apr 2020",
                "SA, USA",
                "Building database tables"
            )
        )
        workExperienceList.add(
            Experience(
                1,R.drawable.mcf_cer_logo,
                "Database admin",
                "Microsoft" ,
                "Oct 2017 - Jul 2018",
                "SF, USA",
                "Managing SQLserver database"
            )
        )
        workExperienceList.add(
            Experience(
                1,R.drawable.ibm_logo,
                "Database admin",
                "IBM" ,
                "Apr 2015 - Oct 2017",
                "NY, USA",
                "Managing AS400 database"
            )
        )
        user.workExperienceList=workExperienceList

        user.phone="+1(641)164-5544"
        user.email="developer@gmail.com"
        user.linkedIn="https://www.linkedin.com/in/muntinvoa"
        user.github="https://github.com/muntinvoa"
        user.pdf="PDF"
        return user
    }

    private fun getUserDataFromSP(): User {
        return Gson()
            .fromJson(
                MSharedPreference?.getString("userData", ""), User::class.java
            )
    }

    private fun setupViewPager(tabViewpager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(HomeFragment.newInstance(user), "Home")
        adapter.addFragment(AboutMeFragment.newInstance(user), "About Me")
        adapter.addFragment(WorkFragment.newInstance(user), "Work")
        adapter.addFragment(ContactFragment.newInstance(user), "Contact")

        tabViewpager.adapter = adapter
    }

    private suspend fun getUserName(): String {
        val userId = intent.extras?.getLong("userId")
        val user = USER_REPO?.getUserUsingID(userId.toString())
        return user?.fName + " " + user?.lName
//        if (user!=null)
//            findViewById<TextView>(R.id.tv_welcome).text= "Welcome ${user.fName} ${user.lName}"
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_tellegram -> showSendMessageDialog("Telegram")
        R.id.action_linkedIn -> showSendMessageDialog("LinkedIn")
        R.id.action_whatsapp -> showSendMessageDialog("Whatsapp")
        R.id.action_gmail -> showSendMessageDialog("Gmail")
        else -> super.onOptionsItemSelected(item)
    }

    private fun showSendMessageDialog(s: String): Boolean {
        AlertDialog.Builder(this)
            .setTitle(s)
            .setMessage("Would you like to send a Message using $s Application")
            .setPositiveButton("Ok",DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            .create()
            .show()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}