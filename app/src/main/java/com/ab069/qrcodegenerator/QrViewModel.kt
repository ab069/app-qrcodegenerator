package com.ab069.qrcodegenerator

import android.graphics.Bitmap
import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class QrType { URL, TEXT }

data class QrUiState(
    val input: String = "",
    val qrType: QrType = QrType.URL,
    val qrBitmap: Bitmap? = null,
    val isGenerating: Boolean = false
) {
    val isValid: Boolean get() = input.isNotBlank()
}

class QrViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(QrUiState())
    val uiState: StateFlow<QrUiState> = _uiState.asStateFlow()

    private var generateJob: Job? = null

    fun onInputChanged(value: String) {
        _uiState.update { it.copy(input = value) }
        scheduleGenerate(value)
    }

    fun onQrTypeChanged(type: QrType) {
        _uiState.update { it.copy(qrType = type) }
        scheduleGenerate(_uiState.value.input)
    }

    private fun scheduleGenerate(content: String) {
        generateJob?.cancel()
        if (content.isBlank()) {
            _uiState.update { it.copy(qrBitmap = null, isGenerating = false) }
            return
        }
        generateJob = viewModelScope.launch {
            _uiState.update { it.copy(isGenerating = true) }
            val bitmap = withContext(Dispatchers.Default) { buildQrBitmap(content) }
            _uiState.update { it.copy(qrBitmap = bitmap, isGenerating = false) }
        }
    }

    private fun buildQrBitmap(content: String): Bitmap? {
        return try {
            val size = 512
            val hints = mapOf(EncodeHintType.MARGIN to 1)
            val matrix = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints)
            val bmp = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            for (x in 0 until size) {
                for (y in 0 until size) {
                    bmp.setPixel(x, y, if (matrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            bmp
        } catch (_: Exception) {
            null
        }
    }
}
