package com.qa.lab4.repository;

import com.qa.lab4.dto.request.CreateTaskRequest;
import com.qa.lab4.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public void createTask(CreateTaskRequest request) {
        String vulnSql = "INSERT INTO task (id, name, done) VALUES" +
                "(" + request.getId() + ", '" + request.getName() + "', " + request.getDone() + ")";
        jdbcTemplate.update(vulnSql);
    }

    public Optional<Task> getById(Long id) {
        String vulnSql = "SELECT * FROM task WHERE id = " + id;
        List<Task> tasks = jdbcTemplate.query(vulnSql, (rs, rowNum) -> {
            Task task = new Task();
            task.setId(rs.getLong("id"));
            task.setName(rs.getString("name"));
            task.setDone(rs.getBoolean("done"));
            return task;
        });

        return tasks.stream().findFirst();
    }

    public List<Task> getAll() {
        return jdbcTemplate.query("SELECT * FROM task", (rs, rowNum) -> {
            Task task = new Task();
            task.setId(rs.getLong("id"));
            task.setName(rs.getString("name"));
            task.setDone(rs.getBoolean("done"));
            return task;
        });
    }

}
