//package com.example.pocketguardian.firestore
//
//import android.content.Context
//import android.net.Uri
//import android.util.Log
//import android.widget.Toast
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
////import com.mae.apevent.models.Event
////import com.mae.apevent.models.User
////import com.mae.apevent.models.UserEvent
////import com.mae.apevent.models.UserSaved
////import com.mae.apevent.ui.activities.*
////import com.mae.apevent.ui.fragments.HomeFragment
////import com.mae.apevent.ui.fragments.ProfileFragment
////import com.mae.apevent.ui.fragments.ScheduleFragment
////import com.mae.apevent.utilities.Constants
//import kotlin.collections.ArrayList
//
//class FirestoreClass {
//    private val mFireStore = FirebaseFirestore.getInstance()
//
//    fun registerUser(activity: RegisterActivity, userInfo: User){
//        mFireStore.collection(Constants.USERS)
//            .document(userInfo.id)
//            .set(userInfo, SetOptions.merge())
//            .addOnSuccessListener {
//                Toast.makeText(activity, "Registration successful!", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener { e ->
//                Toast.makeText(activity, "Error: $e", Toast.LENGTH_SHORT).show()
//            }
//
//    }
//
//    fun getUser(activity: LoginActivity){
//        val currUser = FirebaseAuth.getInstance().currentUser
//        if (currUser != null){
//            FirebaseAuth.getInstance().currentUser?.let {
//                mFireStore.collection(Constants.USERS)
//                    .document(it.uid)
//                    .get()
//                    .addOnSuccessListener { document ->
//                        val user = document.toObject(User::class.java)
//                        if (user!=null){
//                            Constants.LOGGED_IN_NAME = user.name
//                            Constants.LOGGED_IN_ID = user.id
//                            Constants.LOGGED_IN_IMAGE = user.image
//                            activity.loginSuccess(user)
//                        }
//                    }
//                    .addOnFailureListener { e -> Log.e("Error", e.toString()) }
//            }
//        }
//
//    }
//
//    fun getUserInfo(){
//        FirebaseAuth.getInstance().currentUser?.uid?.let {
//            Log.e("ID is ", it.toString())
//            mFireStore.collection(Constants.USERS)
//                .document(it)
//                .get()
//                .addOnSuccessListener { document ->
//                    val user = document.toObject(User::class.java)
//                    if (user!=null){
//                        Log.e("user", user.toString())
//                        Constants.LOGGED_IN_NAME = user.name
//                        Constants.LOGGED_IN_ID = user.id
//                        Constants.LOGGED_IN_IMAGE = user.image
//                    } else { Log.e("Uhh", "nope") }
//                }
//                .addOnFailureListener { e ->
//                    Log.e("Error", e.toString())
//                }
//        }
//    }
//
//    fun getUserID(): String{
//        return FirebaseAuth.getInstance().currentUser?.uid.toString()
//    }
//
//    fun addEvent(activity: NewEventActivity, eventInfo: Event){
//        mFireStore.collection(Constants.EVENTS)
//            .document(eventInfo.id)
//            .set(eventInfo, SetOptions.merge())
//            .addOnSuccessListener {
//                Toast.makeText(activity, "Event submitted!", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener { e ->
//                Toast.makeText(activity, "Error: $e", Toast.LENGTH_SHORT).show()
//            }
//        Log.e("EVENTINFO","ENTERED1")
//    }
//
//    fun addImage(activity: NewEventActivity, uri: Uri?, imgID: String){
//        val imgRef = FirebaseStorage.getInstance().reference.child("images/$imgID")
//        imgRef.putFile(uri!!)
//            .addOnSuccessListener { snapshot ->
//                snapshot.metadata!!.reference!!.downloadUrl
//                    .addOnSuccessListener { uri ->
//                        run {
//                            activity.addedImage(uri.toString())
//                        }
//                    }
//            }
//            .addOnFailureListener{
//                Log.e("Image upload", "Failed")
//            }
//    }
//
//    fun addProfileImage(fragment: ProfileFragment, uri: Uri?, imgID: String){
//        val imgRef = FirebaseStorage.getInstance().reference.child("images/$imgID")
//        imgRef.putFile(uri!!)
//            .addOnSuccessListener { snapshot ->
//                snapshot.metadata!!.reference!!.downloadUrl
//                    .addOnSuccessListener { uri ->
//                        run {
//                            fragment.addedImage(uri.toString())
//                            Constants.LOGGED_IN_IMAGE
//                        }
//                    }
//            }
//            .addOnFailureListener{ Log.e("Image upload", "Failed") }
//    }
//
//    fun addProfileImageToUser(userID: String, imageID: String){
//        val hashmap = HashMap<String, Any>()
//        hashmap["image"] = imageID
//        mFireStore.collection(Constants.USERS)
//            .document(userID)
//            .update(hashmap)
//            .addOnSuccessListener { Log.e("Img", "Updated") }
//            .addOnFailureListener{ Log.e("Img", "Didnt update") }
//    }
//
//    fun getEvents(fragment: HomeFragment){
//        mFireStore.collection(Constants.EVENTS)
//            .get()
//            .addOnSuccessListener { document ->
//                val eventList: ArrayList<Event> = ArrayList()
//                for (i in document.documents){
//                    val event = i.toObject(Event::class.java)
//                    if (event != null) {
//                        event.id = i.id
//                        eventList.add(event)
//                    }
//                }
//                fragment.fetchedEvents(eventList)
//            }
//    }
//
//    fun getRegisteredEvents(fragment: ScheduleFragment, userID: String){
//        val eventList: ArrayList<Event> = ArrayList()
//        mFireStore.collection(Constants.USER_EVENTS)
//            .whereEqualTo("userId", userID)
//            .get()
//            .addOnSuccessListener { document ->
//                for (i in document.documents){
//                    mFireStore.collection(Constants.EVENTS)
//                        .whereEqualTo("id", i["eventId"])
//                        .get()
//                        .addOnSuccessListener { document2 ->
//                            for (j in document2.documents){
//                                val event = j.toObject(Event::class.java)
//                                if (event != null){
//                                    event.id = j.id
//                                    eventList.add(event)
//                                }
//                            }
//                            fragment.fetchedEvents(eventList)
//                        }
//                }
//            }
//            .addOnCompleteListener{
//                fragment.fetchedEvents(eventList)
//            }
//    }
//
//    fun getSavedEvents(fragment: ScheduleFragment, userID: String){
//        val eventList: ArrayList<Event> = ArrayList()
//        mFireStore.collection(Constants.USER_SAVED)
//            .whereEqualTo("userId", userID)
//            .get()
//            .addOnSuccessListener { document ->
//                for (i in document.documents){
//                    mFireStore.collection(Constants.EVENTS)
//                        .whereEqualTo("id", i["eventId"])
//                        .get()
//                        .addOnSuccessListener { document2 ->
//                            for (j in document2.documents){
//                                val event = j.toObject(Event::class.java)
//                                if (event != null){
//                                    event.id = j.id
//                                    eventList.add(event)
//                                }
//                            }
//                            fragment.fetchedEvents(eventList)
//                        }
//                }
//
//            }
//            .addOnCompleteListener{ fragment.fetchedEvents(eventList) }
//    }
//
//    fun getCreatedEvents(fragment: ScheduleFragment, userID: String) {
//        val eventList: ArrayList<Event> = ArrayList()
//        mFireStore.collection(Constants.EVENTS)
//            .whereEqualTo("user", userID)
//            .get()
//            .addOnSuccessListener { document ->
//                for (i in document.documents) {
//                    val event = i.toObject(Event::class.java)
//                    if (event != null) {
//                        event.id = i.id
//                        eventList.add(event)
//                    }
//                }
//                fragment.fetchedEvents(eventList)
//            }
//    }
//
//    fun getEventDetails(context: EventActivity, eventID: String) {
//        mFireStore.collection(Constants.EVENTS)
//            .document(eventID)
//            .get()
//            .addOnSuccessListener { document ->
//                context.gotEventDetails(document.toObject(Event::class.java)!!)
//            }
//            .addOnFailureListener{ exception ->
//                Log.e("Event error", "Failed to fetch event: " + exception.message)
//            }
//    }
//
//    fun addUserEvent(activity: EventActivity, userEvent: UserEvent){
//        mFireStore.collection(Constants.USER_EVENTS)
//            .whereEqualTo("userId", userEvent.userId)
//            .whereEqualTo("eventId", userEvent.eventId)
//            .get()
//            .addOnSuccessListener { document ->
//                if (document.size() > 0)
//                    Toast.makeText(activity, "You're already registered for this event!", Toast.LENGTH_SHORT).show()
//                else{
//                    mFireStore.collection(Constants.USER_EVENTS)
//                        .document()
//                        .set(userEvent, SetOptions.merge())
//                        .addOnSuccessListener { activity.addedUserEvent() }
//                        .addOnFailureListener{
//                            Toast.makeText(activity, "Failed to register for event", Toast.LENGTH_SHORT).show()
//                        }
//                }
//            }
//    }
//
//    fun addUserSaved(activity: EventActivity, userSaved: UserSaved){
//        Log.e("EVENTINFO","ENTERED")
//        mFireStore.collection(Constants.USER_SAVED)
//            .whereEqualTo("userId", userSaved.userId)
//            .whereEqualTo("eventId", userSaved.eventId)
//            .get()
//            .addOnSuccessListener { document ->
//                if (document.size() > 0){
//                    Toast.makeText(activity, "You're already saved this event!", Toast.LENGTH_SHORT).show()
//                }
//                else{
//                    mFireStore.collection(Constants.USER_SAVED)
//                        .document()
//                        .set(userSaved, SetOptions.merge())
//                        .addOnSuccessListener {
//                            activity.addedUserSaved()
//                        }
//                        .addOnFailureListener{
//                            Toast.makeText(activity, "Failed to save event", Toast.LENGTH_SHORT).show()
//                        }
//                }
//            }
//        Log.e("EVENTINFO","LEFT")
//    }
//
//    fun deleteEvent(fragment: Context, eventId: String){
//        mFireStore.collection(Constants.EVENTS)
//            .document(eventId)
//            .delete()
//            .addOnSuccessListener {
//                Toast.makeText(fragment, "Deleted successfully", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener{
//                Toast.makeText(fragment, "Failed to delete", Toast.LENGTH_SHORT).show()
//            }
//    }
//
//    fun deleteUserEvent(fragment: Context, eventId: String, userId: String){
//        mFireStore.collection(Constants.USER_EVENTS)
//            .whereEqualTo("userId", userId)
//            .whereEqualTo("eventId", eventId)
//            .get()
//            .addOnSuccessListener { document ->
//                for (i in document.documents){
//                    i.reference.delete()
//                }
//                Toast.makeText(fragment, "Withdrawn successfully!", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener{
//                Toast.makeText(fragment, "Failed to withdraw", Toast.LENGTH_SHORT).show()
//            }
//    }
//
//
//    fun deleteUserSaved(fragment: Context, eventId: String, userId: String){
//        mFireStore.collection(Constants.USER_SAVED)
//            .whereEqualTo("userId", userId)
//            .whereEqualTo("eventId", eventId)
//            .get()
//            .addOnSuccessListener { document ->
//                for (i in document.documents){
//                    i.reference.delete()
//                }
//                Toast.makeText(fragment, "Removed successfully!", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener{
//                Toast.makeText(fragment, "Failed to withdraw", Toast.LENGTH_SHORT).show()
//            }
//    }
//}