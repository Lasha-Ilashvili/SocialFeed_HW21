package com.example.socialfeed_hw22.presentation.mapper.post

import com.example.socialfeed_hw22.domain.model.OwnerDomain
import com.example.socialfeed_hw22.domain.model.PostDomain
import com.example.socialfeed_hw22.presentation.model.Owner
import com.example.socialfeed_hw22.presentation.model.Post
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun PostDomain.toPresentation(): Post {
    return Post(
        id = id,
        images = images,
        title = title,
        comments = comments,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toPresentation()
    )
}

fun OwnerDomain.toPresentation(): Owner {
    return Owner(
        firstName = firstName,
        lastName = lastName,
        profile = profile,
        postDate = postDate.formatDate()
    )
}

private fun Long.formatDate(): String {
    return SimpleDateFormat(
        "dd MMMM 'at' h:mm a",
        Locale.getDefault()
    ).format(
        Date(this * 1000L)
    )
}