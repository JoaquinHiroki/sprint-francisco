package com.davinchicoder.springgraphql.factory;

import com.davinchicoder.springgraphql.dto.PostDto;
import com.davinchicoder.springgraphql.entity.Post;

public abstract class PostFactory {

    public abstract Post createPost(PostDto postDto);
}
