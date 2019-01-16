package com.github.nut077.springreact.service;

import com.github.nut077.springreact.entity.Project;

public interface ProjectService {
    Project findByProjectIdentifier(String projectIdentifier);
}
