package model.repository;

import model.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by piotrsa on 25/04/18.
 */
public interface MailRepository extends JpaRepository<Mail, Integer> {
}
