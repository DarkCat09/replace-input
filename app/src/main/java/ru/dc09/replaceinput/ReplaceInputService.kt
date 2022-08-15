package ru.dc09.replaceinput

import android.accessibilityservice.AccessibilityService
import android.os.Bundle
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class ReplaceInputService : AccessibilityService() {
    override fun onInterrupt() {}
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        if (event == null || event.source == null) return

        val field: AccessibilityNodeInfo = event.source
        val txt: String = field.text?.toString()
            ?.lowercase()
            ?.replace('ё', 'е')
            ?: ""

        val args = Bundle()
        args.putCharSequence(
            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
            txt
        )
        field.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args)
    }
}