package com.mdub.salesforce

import android.app.Application
import android.content.Context
import android.util.Log
import com.salesforce.marketingcloud.MCLogListener
import com.salesforce.marketingcloud.MarketingCloudConfig
import com.salesforce.marketingcloud.MarketingCloudSdk
import com.salesforce.marketingcloud.notifications.NotificationCustomizationOptions

class SalesforceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MarketingCloudSdk.setLogListener(MCLogListener.AndroidLogListener())

        MarketingCloudSdk.init(this, MarketingCloudConfig.builder().apply {
            setApplicationId(BuildConfig.MOBILE_PUSH_APP_ID)
            setAccessToken(BuildConfig.MOBILE_PUSH_ACCESS_TOKEN)
            setSenderId(BuildConfig.FIREBASE_SENDER_ID)
            setMarketingCloudServerUrl(BuildConfig.MOBILE_PUSH_END_POINT)
            setMid(BuildConfig.MOBILE_PUSH_PROJECT_MID)
            setNotificationCustomizationOptions(
                NotificationCustomizationOptions.create(R.mipmap.ic_launcher)
            )
        }.build(this as Context)) { status ->
            Log.d("salesforce", "isUsable = ${status.isUsable}")
            Log.d("salesforce", "playServicesMessage = ${status.playServicesMessage()}")
            Log.d("salesforce", "status = $status")
        }
    }
}