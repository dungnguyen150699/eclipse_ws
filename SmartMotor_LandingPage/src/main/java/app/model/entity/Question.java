package vn.viettel.app.model.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.viettel.app.model.AbstractModel;

import javax.persistence.*;

@Entity(name="Question")
@Table(name="question")
@NoArgsConstructor
@Data
public class Question extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "order")
    private Integer price;

    @Column(name = "status")
    private Integer status;
}
