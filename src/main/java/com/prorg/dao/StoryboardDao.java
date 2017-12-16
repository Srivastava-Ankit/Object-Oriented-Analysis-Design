package com.prorg.dao;

import com.prorg.helper.result.Response;
import com.prorg.model.Storyboard;
import com.prorg.model.User;

import java.util.List;

public interface StoryboardDao {
    Response<Integer> save(Storyboard storyboard);
    Response<Storyboard> findById(int storyboardId);
    Response deleteById(int storyboardId);
    Response update(Storyboard storyboard);
    Response<List<Storyboard>> findByCreator(User creator);
}
