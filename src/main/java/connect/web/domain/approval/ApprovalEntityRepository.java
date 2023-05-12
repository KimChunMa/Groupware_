package connect.web.domain.approval;

import connect.web.domain.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalEntityRepository  extends JpaRepository<ApprovalEntity, Integer > {

    //
    @Query( value = "select approval_status from approval where member_no = :pk" ,nativeQuery = true )
    String findByStatus( @Param("pk") int pk);

/*
    @Query( value =" select M.MEMBER_NO, M.MEMBER_RANK, M.PART_NO , A.APPROVAL_NO, A.APPROVAL_CONTENT, A.APPROVAL_DATA , A.APPROVAL_STATUS, A.APPROVAL_TITLE, A.APPROVAL_WRITER " +
                    " FROM MEMBER M, APPROVAL A " +
                    " WHERE M.MEMBER_NO = A.MEMBER_NO and M.MEMBER_RANK = :rank and M.PART_NO = :part" , nativeQuery = true )
    List<ApprovalEntity>findByRank( @Param("rank") int rank , @Param("part") int part );*/


    /*STATUS 상태에따른 서류*/
    @Query( value ="select  m.member_no," +
            " m.member_name," +
            "        m.member_rank," +
            "        p.part_no ," +
            "        p.part_name," +
            "        a.approval_no," +
            "        a.approval_content," +
            "        a.approval_data," +
            "        a.approval_status," +
            "        a.approval_title ," +
            "        a.approval_writer " +
            "   from member m, part p , approval a" +
            "          where m.member_no = a.member_no and p.part_no = m.part_no and a.approval_status = :status" , nativeQuery = true )
    List<ApprovalEntity>findByWatch( @Param("status") String status );


    @Modifying
    //approval_no를 인수로 받아 서류승인완료되면 1단계씩 approval_status 1씩 증가하도록
    @Query( value = "update approval" +
            " set approval_status = (approval_status + 1)" +
            " where approval_no = :approvalNo" ,nativeQuery = true)
    int statusupdate( @Param("approvalNo")int approvalNo);




}
