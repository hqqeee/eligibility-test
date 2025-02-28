package com.sysgears.eligibility.mapper;

import com.sysgears.eligibility.model.EligibilityRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.Optional;

@Mapper
public interface EligibilityMapper {

    @Select("SELECT * FROM eligibility_records WHERE member_unique_id = #{employeeId} AND date_of_birth = #{dateOfBirth} AND eligibility_start_date <= date('now')")
    Optional<EligibilityRecord> findEmployeeByIdAndDob(String employeeId, String dateOfBirth);

    @Select("SELECT * FROM eligibility_records WHERE first_name = #{firstName} AND last_name = #{lastName} AND date_of_birth = #{dateOfBirth} AND eligibility_start_date <= date('now')")
    Optional<EligibilityRecord> findDependentByNameAndDob(String firstName, String lastName, String dateOfBirth);
}
