package jp.langstack.domain.card;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import jp.langstack.interfaces.Activity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

    private final EntityManager entityManager;
    private final CardRepository cardRepo;

    private static final String QUERY_GET_NEW_CARDS 
    = "SELECT DISTINCT card FROM CardEntity card LEFT JOIN FETCH card.genre ORDER BY card.postDate DESC";
    
    // MEMO：JPQLでは直接「LIMIT句」はクエリに書かない。entityManager経由で「setMaxResult()」で指定するのだそう。
    // https://www.baeldung.com/jpa-limit-query-results
    public List<CardEntity> getTheLastFiveCards() {
        return entityManager.createQuery(QUERY_GET_NEW_CARDS, CardEntity.class).setMaxResults(5).getResultList();
    }

    // MEMO：「COUNT() × GROUP BY」はNativeQueryで実行してあげる必要がある。
    // https://kumagoro-95.hatenablog.com/entry/2021/01/05/170711
    public Set<Activity> getTheLastFiveDaysActivity() {
        Set<Activity> activities = new TreeSet<>(Comparator.comparing(Activity::getPostDate).reversed());
        activities.addAll(cardRepo.getTheLastFiveDaysActivity(LocalDate.now().minusDays(4)));
        return activities;    
    }

    public int getAllCardCount() {
        return (int) cardRepo.count();
    }
    
}