package kr.co.swtest.springframework.dataaccess.dto;

/**
 * 고객 DTO
 * 
 * @author <a href="mailto:scroogy@swtest.co.kr">최영목</a>
 */
public class CustomerDto {

    /** 아이디 */
    private Integer id;

    /** 고객명 */
    private String name;

    /** 이메일 */
    private String email;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    /**
     * 생성자
     */
    public CustomerDto() {
        // 아무일도 하지 않음
    }

    /**
     * 생성자
     *
     * @param id 아이디
     * @param name 고객명
     * @param email 이메일
     */
    public CustomerDto(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // -------------------------------------------------------------------------
    // Getter and Setter
    // -------------------------------------------------------------------------

    /**
     * @return 아이디
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @param id 아이디
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 고객명
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name 고객명
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 이메일
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email 이메일
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
