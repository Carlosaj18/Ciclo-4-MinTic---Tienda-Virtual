package com.example.proyectacuenta

import android.util.Patterns

// Es una expresion regular para validar un correo electronico
fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()