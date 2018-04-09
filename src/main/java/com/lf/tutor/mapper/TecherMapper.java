package com.lf.tutor.mapper;

import com.lf.tutor.domain.Techer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Mapper
public interface TecherMapper {
    /**
     * 新增教员
     * @param techer
     */
    @Insert("insert into techer(techer_id,techer_name,techer_sex,techer_age,techer_level_id,is_valid," +
            "score,institute_id,edu_level_id,edu_th_id,subject_id,grade_id,self_comment,time_type_id," +
            "created_date,updated_date,is_approve,phone,intro,exp) values(#{techerId},#{techerName},#{techerSex},#{techerAge},#{techerLevelId}," +
            "'Y',3,#{instituteId},'1',#{eduThId},#{subjectId},#{gradeId},#{selfComment}," +
            "#{timeTypeId},now(),now(),'N',#{phone},#{intro},#{exp})")
    void insert(Techer techer);

    /**
     * 多条件查询教员信息
     * @param techer
     * @return
     */
    @Select("<script>" +
            "select * from techer where is_valid = 'Y' " +
            "<if test=\"techerSex != null \">and techer_sex=#{techerSex} </if>" +
            "<if test=\"techerName != null\">and techer_name=#{techerName} </if>" +
            "<if test=\"instituteId != null\">and institute_id=#{instituteId} </if>" +
            "<if test=\"eduLevelId != null\">and edu_level_id=#{eduLevelId} </if>" +
            "<if test=\"isApprove != null\">and is_approve=#{isApprove} </if>" +
            "<if test=\"techerLevelId != null\">and techer_level_id=#{techerLevelId} </if>" +
            "<if test=\"timeTypeId != null\">and find_in_set(#{timeTypeId},time_type_id)  </if>" +
            "<if test=\"subjectId != null\">and find_in_set(#{subjectId},subject_id)  </if>" +
            "<if test=\"gradeId != null\">and find_in_set(#{gradeId},grade_id)  </if>" +
            "order by updated_date desc" +
            "</script>")
    List<Techer> getTecherList(Techer techer);

    /**
     * 获取匹配的前3教员列表
     * @param techer
     * @return
     */
    @Select("<script>" +
            "select * from techer where is_valid = 'Y' " +
            "<if test=\"techerSex != null \">and techer_sex=#{techerSex} </if>" +
            "<if test=\"eduLevelId != null\">and edu_level_id=#{eduLevelId} </if>" +
            "<if test=\"techerLevelId != null\">and techer_level_id=#{techerLevelId} </if>" +
            "<if test=\"timeTypeId != null\">and find_in_set(#{timeTypeId},time_type_id)  </if>" +
            "<if test=\"subjectId != null\">and find_in_set(#{subjectId},subject_id)  </if>" +
            "<if test=\"gradeId != null\">and find_in_set(#{gradeId},grade_id)  </if>" +
            "order by score desc limit 0,3" +
            "</script>")
    List<Techer> getMatchTecherList(Techer techer);

    /**
     * 获取评分最高的3个教员
     * @return
     */
    @Select("select * from techer where is_valid = 'Y' and is_approve = 'Y' order by score desc limit 0,3")
    List<Techer> getTop3TecherList();

    /**
     * 根据教员ID获取教员信息
     * @param techerId
     * @return
     */
    @Select("select * from techer where techer_id=#{techerId} and is_valid='Y'")
    Techer getTecherById(String techerId);

    /**
     * 更新教员信息
     * @param techer
     */
    @Update("update techer set " +
            "techer_name=#{techerName}," +
            "techer_sex=#{techerSex}," +
            "techer_age=#{techerAge}," +
            "techer_level_id=#{techerLevelId}," +
            "is_valid=#{isValid}," +
            "score=#{score}," +
            "institute_id=#{instituteId}," +
            "edu_level_id=#{eduLevelId}," +
            "edu_th_id=#{eduThId}," +
            "subject_id=#{subjectId}," +
            "grade_id=#{gradeId}," +
            "self_comment=#{selfComment}," +
            "time_type_id=#{timeTypeId}," +
            "is_approve=#{isApprove}," +
            "phone=#{phone}," +
            "intro=#{intro}," +
            "exp=#{exp}," +
            "updated_date=now()" +
            " where techer_id = #{techerId} and is_valid = 'Y'")
    void update(Techer techer);
}
