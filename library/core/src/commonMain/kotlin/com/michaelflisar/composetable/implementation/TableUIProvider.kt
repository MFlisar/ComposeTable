package com.michaelflisar.composetable.implementation

internal object TableUIProvider {
    val impl: TableUI.Provider =
        throw NotImplementedError("TableUIProvider is not implemented. Do not use core module directly, but use a UI implementation module (e.g. material3 or styled) instead.")
}