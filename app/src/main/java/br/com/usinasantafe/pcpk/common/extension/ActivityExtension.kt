package br.com.usinasantafe.pcpk.common.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun Activity.hideKeyboard(){
    val imm: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view: View? = currentFocus
    if(view == null){
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
//
//fun showToast(message: String, context: Context){
//    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
//}
//
//fun Fragment.showGenericAlertDialog(message: String, context: Context){
//    AlertDialog.Builder(context).apply {
//        setMessage(message)
//        setPositiveButton(getString(R.string.texto_padrao_ok)){ dialog, _ ->
//            dialog.dismiss()
//        }
//    }.show()
//}
//
//fun Activity.showGenericAlertDialog(message: String, context: Context){
//    AlertDialog.Builder(context).apply {
//        setMessage(message)
//        setPositiveButton(getString(R.string.texto_padrao_ok)){ dialog, _ ->
//            dialog.dismiss()
//        }
//    }.show()
//}
//
//fun setListenerButtonsGeneric(layoutBotoesBinding: LayoutBotoesBinding, editText: EditText){
//    with(layoutBotoesBinding){
//        buttonNum0.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum0.text)
//        }
//        buttonNum1.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum1.text)
//        }
//        buttonNum2.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum2.text)
//        }
//        buttonNum3.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum3.text)
//        }
//        buttonNum4.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum4.text)
//        }
//        buttonNum5.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum5.text)
//        }
//        buttonNum6.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum6.text)
//        }
//        buttonNum7.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum7.text)
//        }
//        buttonNum8.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum8.text)
//        }
//        buttonNum9.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum9.text)
//        }
//        buttonCancPadrao.setOnClickListener {
//            if (editText.text.isNotEmpty()) {
//                editText.setText(editText.text.substring(0, editText.text.length - 1))
//            }
//        }
//    }
//}
//
//fun setListenerButtonsGenericSUpdate(layoutBotoesSAtualBinding: LayoutBotoesSAtualBinding, editText: EditText){
//    with(layoutBotoesSAtualBinding) {
//        buttonNum0.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum0.text)
//        }
//        buttonNum1.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum1.text)
//        }
//        buttonNum2.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum2.text)
//        }
//        buttonNum3.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum3.text)
//        }
//        buttonNum4.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum4.text)
//        }
//        buttonNum5.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum5.text)
//        }
//        buttonNum6.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum6.text)
//        }
//        buttonNum7.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum7.text)
//        }
//        buttonNum8.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum8.text)
//        }
//        buttonNum9.setOnClickListener {
//            editText.setText("${editText.text}" + buttonNum9.text)
//        }
//        buttonCancPadrao.setOnClickListener {
//            if (editText.text.isNotEmpty()) {
//                editText.setText(editText.text.substring(0, editText.text.length - 1))
//            }
//        }
//    }
//}
//
//fun setListenerButtonsGenericCVirgula(layoutBotoesSAtualBinding: LayoutBotoesSAtualBinding, editText: EditText) {
//    with(layoutBotoesSAtualBinding) {
//        buttonNum0.setOnClickListener {
//            editText.setText(addCaracterVirgula("0", editText))
//        }
//        buttonNum1.setOnClickListener {
//            editText.setText(addCaracterVirgula("1", editText))
//        }
//        buttonNum2.setOnClickListener {
//            editText.setText(addCaracterVirgula("2", editText))
//        }
//        buttonNum3.setOnClickListener {
//            editText.setText(addCaracterVirgula("3", editText))
//        }
//        buttonNum4.setOnClickListener {
//            editText.setText(addCaracterVirgula("4", editText))
//        }
//        buttonNum5.setOnClickListener {
//            editText.setText(addCaracterVirgula("5", editText))
//        }
//        buttonNum6.setOnClickListener {
//            editText.setText(addCaracterVirgula("6", editText))
//        }
//        buttonNum7.setOnClickListener {
//            editText.setText(addCaracterVirgula("7", editText))
//        }
//        buttonNum8.setOnClickListener {
//            editText.setText(addCaracterVirgula("8", editText))
//        }
//        buttonNum9.setOnClickListener {
//            editText.setText(addCaracterVirgula("9", editText))
//        }
//        buttonCancPadrao.setOnClickListener {
//            editText.setText(remCaracterVirgula(editText))
//        }
//    }
//
//
//}
//
//fun addCaracterVirgula(caracter: String, editText: EditText): String {
//    var text = "${editText.text.toString().replace(",", "")}" + caracter
//    var textBuilder: StringBuilder = StringBuilder(text).insert(text.length - 1, ',')
//    return textBuilder.toString()
//}
//
//fun remCaracterVirgula(editText: EditText): String {
//    var textBuilder: StringBuilder = StringBuilder("")
//    if (editText.text.isNotEmpty()) {
//        var text = "${editText.text.toString().replace(",", "")}"
//        text = text.substring(0, text.length - 1)
//        if(text.isNotEmpty()){
//            textBuilder = StringBuilder(text).insert(text.length - 1, ',')
//        }
//    }
//    return textBuilder.toString()
//}

fun AppCompatActivity.replaceFragment(@IdRes id: Int, fragment: Fragment){
    if(supportFragmentManager.findFragmentById(id) == null){
        supportFragmentManager.beginTransaction().apply {
            add(id, fragment)
            commit()
        }
    } else {
        supportFragmentManager.beginTransaction().apply {
            replace(id, fragment)
            addToBackStack(null)
            commit()
        }
    }
    hideKeyboard()
}
//
//fun Fragment.onBackPressed(callback: () -> Unit){
//    val funReturn: OnBackPressedCallback = object : OnBackPressedCallback(true) {
//        override fun handleOnBackPressed() {
//            callback.invoke()
//        }
//    }
//    requireActivity().onBackPressedDispatcher.addCallback(
//        this, funReturn
//    )
//}
