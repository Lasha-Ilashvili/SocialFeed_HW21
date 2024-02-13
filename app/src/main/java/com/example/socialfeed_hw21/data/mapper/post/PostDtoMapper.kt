package com.example.socialfeed_hw21.data.mapper.post

import com.example.socialfeed_hw21.data.model.post.OwnerDto
import com.example.socialfeed_hw21.data.model.post.PostDto
import com.example.socialfeed_hw21.domain.model.OwnerDomain
import com.example.socialfeed_hw21.domain.model.PostDomain

fun PostDto.toDomain(): PostDomain {
    return PostDomain(
        id = id,
        images = images,
        title = title,
        comments = comments,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toDomain()
    )
}

fun OwnerDto.toDomain(): OwnerDomain {
    return OwnerDomain(
        firstName = firstName,
        lastName = lastName,
        profile = profile,
        postDate = postDate
    )
}