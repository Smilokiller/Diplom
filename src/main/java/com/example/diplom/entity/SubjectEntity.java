package com.example.diplom.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subject", schema = "markcheck", catalog = "")
public class SubjectEntity {


    private int id;
    private String subjectName;
    private int teacherId;
    private Integer groupId;
    private String groupName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "subject_name")
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Basic
    @Column(name = "teacher_id")
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectEntity that = (SubjectEntity) o;
        return id == that.id &&
                teacherId == that.teacherId &&
                Objects.equals(subjectName, that.subjectName) &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, subjectName, teacherId, groupId, groupName);
    }
}
