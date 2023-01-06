package com.gyms.kollek.components_old

sealed class PermissionAction {
    object OnPermissionGranted : PermissionAction()
    object OnPermissionDenied : PermissionAction()
}