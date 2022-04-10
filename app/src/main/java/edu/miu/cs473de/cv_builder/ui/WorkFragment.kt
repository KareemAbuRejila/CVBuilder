package edu.miu.cs473de.cv_builder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.miu.cs473de.cv_builder.R
import edu.miu.cs473de.cv_builder.models.User
import edu.miu.cs473de.cv_builder.ui.adapters.ListAdapter

private const val USER = "user"

class WorkFragment : Fragment() {
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable(USER) as User
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpList(view)
    }

    private fun setUpList(view: View) {
        user?.let{
            val adapter= ListAdapter()
            val rv_list=view.findViewById<RecyclerView>(R.id.rv_workExperienceList)
            adapter.setAdapterType(ListAdapter.EXPERIENCE_TYPE)
            adapter.addExperiences(it.workExperienceList!!)
            rv_list.adapter=adapter
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(user: User) =
            WorkFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER,user)
                }
            }
    }
}