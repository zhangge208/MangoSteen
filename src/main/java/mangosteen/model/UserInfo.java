package mangosteen.model;

import java.time.LocalDateTime;

public class UserInfo {
    private Long id;
    private String username;

    private String password;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public UserInfo(Long id, String username, String password, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public UserInfo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserInfo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserInfo setPassword(String password) {
        this.password = password;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public UserInfo setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public UserInfo setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
