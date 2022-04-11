package com.example.hireaskill.Settings

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.hireaskill.R
import com.example.hireaskill.databinding.FragmentSignupFragmentBinding
import com.example.hireaskill.databinding.FragmentUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.util.*

class UserProfileFragment : Fragment() {

    private lateinit var fireAuth : FirebaseAuth
    private lateinit var binding : FragmentUserProfileBinding
    private lateinit var dialog: Dialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserProfileBinding.inflate(layoutInflater)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fireAuth= FirebaseAuth.getInstance()

        val db = FirebaseFirestore.getInstance()
        val ID = FirebaseAuth.getInstance().currentUser?.uid.toString()

        val name : TextView = view.findViewById(R.id.name)
        //val email : TextView = view.findViewById(R.id.email)
        val profile : ImageView = view.findViewById(R.id.profile_pic)
        val number : TextView = view.findViewById(R.id.phone)


        profile.setOnClickListener{

            val intent = Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent,0)
        }

       val save : Button = view.findViewById(R.id.savebtn)
        save.setOnClickListener {

            showProgressBar()

            val user : MutableMap<String,Any> = hashMapOf()

            user["username"]=name.text.toString()

            user["number"]=number.text.toString()

            Firebase.auth.uid?.let { it1 ->
                val db=FirebaseFirestore.getInstance()
                db.collection("users").document(it1).set(user, SetOptions.merge())
            }?.addOnSuccessListener {

                hideProgressBar()

                Toast.makeText(context, "INFORMATION UPDATED SUCCESSFULLY ", Toast.LENGTH_SHORT).show()

            }
                ?.addOnFailureListener{ e->
                    Toast.makeText(context, "ERROR OCCURED $e ", Toast.LENGTH_SHORT).show()

                }


        }









        //Showing Previous Data


        val docRef: DocumentReference = db.collection("users").document(ID)
        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {

                    val image_url = document.getString("profile_url")
                    if(image_url!=""){
                        Picasso.get().load(image_url).error(R.drawable.profile_person)
                            .into(profile)

                    }

                    val Name = document.getString("username")
                    val Phone = document.getString("number")
                    //val Email = document.getString("email")

                    name.setText(Name)
                    //email.setText(ContactsContract.CommonDataKinds.Email)
                    number.setText(Phone)


                    Log.d("LOGGER", "$name ")
                }
            } else {
                Log.d("LOGGER", "get failed with ", task.exception)
                }





            //Saving Edited Data








        }
    }




    //showing selected image
    var selectedPhotoUri: Uri?=null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val profile : ImageView = view!!.findViewById(R.id.profile_pic)
        if(requestCode==0 && resultCode== Activity.RESULT_OK && data!= null){


            val save : Button = view!!.findViewById(R.id.savebtn)
            val name : TextView = view!!.findViewById(R.id.name)
            //val email : TextView = view.findViewById(R.id.email)
            val profile : ImageView = view!!.findViewById(R.id.profile_pic)
            val number : TextView = view!!.findViewById(R.id.phone)


            selectedPhotoUri =data.data
            val contentResolver = requireActivity().contentResolver
            val bitmap= MediaStore.Images.Media.getBitmap(contentResolver,selectedPhotoUri)
            profile.setImageBitmap(bitmap)

            save.setOnClickListener {

                showProgressBar()

                if(selectedPhotoUri!=null){

                selectedPhotoUri?.let { uploadProfilePic(it) }

                }

                val user : MutableMap<String,Any> = hashMapOf()

                user["username"]=name.text.toString()

                user["number"]=number.text.toString()

                Firebase.auth.uid?.let { it1 ->
                    val db=FirebaseFirestore.getInstance()
                    db.collection("users").document(it1).set(user, SetOptions.merge())
                }?.addOnSuccessListener {

                }
                    ?.addOnFailureListener{ e->
                        Toast.makeText(context, "ERROR OCCURED $e ", Toast.LENGTH_SHORT).show()

                    }

            }


        }

    }

    private fun uploadProfilePic(uri : Uri){


        if (uri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(uri)
            .addOnSuccessListener {
                Log.d("Tag", "Successfully uploaded image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {

                    hideProgressBar()

                    Toast.makeText(context, "INFORMATION UPDATED SUCCESSFULLY ", Toast.LENGTH_SHORT).show()

                    val user:MutableMap<String,Any> = HashMap()
                    user["profile_url"]=it.toString()
                    val db = FirebaseFirestore.getInstance()
                    Firebase.auth.uid?.let { it1 ->
                        db.collection("users").document(it1).set(user,
                            SetOptions.merge())
                    }


                    Log.d("Tag", "File Location: $it")
                }
            }
            .addOnFailureListener {
                Log.d("Tag", "Failed to upload image to storage: ${it.message}")
            }
    }
    private fun showProgressBar(){

        dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_progress)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()


    }



    private fun hideProgressBar(){
        dialog.dismiss()
    }

    }


