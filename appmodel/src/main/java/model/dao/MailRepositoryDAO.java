package model.dao;

import model.entity.Mail;
import model.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by piotrsa on 25/04/18.
 */
@Repository
public class MailRepositoryDAO {

    @Autowired
    private MailRepository mailRepository;

    @Transactional
    public void save(Mail mail){
        mailRepository.save(mail);
    }

}
