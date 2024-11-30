import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.NY5FashLink.model.Advisor;
import com.example.NY5FashLink.service.AdvisorService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class AdvisorServiceTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private AdvisorService advisorService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testFindById_ValidId_ReturnsAdvisor() {
        // Arrange
        String validId = "507f1f77bcf86cd799439011";
        ObjectId objectId = new ObjectId(validId);
        Advisor expectedAdvisor = new Advisor(); // Mocked Advisor object
        when(mongoTemplate.findOne(
                Query.query(Criteria.where("_id").is(objectId)),
                Advisor.class
        )).thenReturn(expectedAdvisor);

        // Act
        Advisor result = advisorService.findById(validId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedAdvisor, result);
        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(Advisor.class));
    }

    @Test
    void testFindById_InvalidId_ReturnsNull() {
        // Arrange
        String invalidId = "invalid-id";

        // Act
        Advisor result = advisorService.findById(invalidId);

        // Assert
        assertNull(result);
        verify(mongoTemplate, never()).findOne(any(Query.class), eq(Advisor.class));
    }

    @Test
    void testFindById_NullId_ReturnsNull() {
        // Act
        Advisor result = advisorService.findById(null);

        // Assert
        assertNull(result);
        verify(mongoTemplate, never()).findOne(any(Query.class), eq(Advisor.class));
    }

    @Test
    void testFindById_ValidIdNotFound_ReturnsNull() {
        // Arrange
        String validId = "507f1f77bcf86cd799439011";
        ObjectId objectId = new ObjectId(validId);
        when(mongoTemplate.findOne(
                Query.query(Criteria.where("_id").is(objectId)),
                Advisor.class
        )).thenReturn(null);

        // Act
        Advisor result = advisorService.findById(validId);

        // Assert
        assertNull(result);
        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(Advisor.class));
    }
}
