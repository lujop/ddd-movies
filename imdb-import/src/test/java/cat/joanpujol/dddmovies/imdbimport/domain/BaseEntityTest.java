package cat.joanpujol.dddmovies.imdbimport.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    @Test
    public void twoObjectsWithSameIdMustBeEqual() {
        SampleEntity o1 = new SampleEntity(new Id("id1"));
        SampleEntity o2 = new SampleEntity(new Id("id1"));

        assertThat(o1).isEqualTo(o2);
    }

    @Test
    public void mustBeEqualtoHimself() {
        SampleEntity o1 = new SampleEntity(new Id("id1"));

        assertThat(o1).isEqualTo(o1);
    }

    @Test
    public void twoObjectsWithSameIdMustHaveSameHashCode() {
        SampleEntity o1 = new SampleEntity(new Id("id1"));
        SampleEntity o2 = new SampleEntity(new Id("id1"));

        assertThat(o1.hashCode()).isEqualTo(o2.hashCode());
    }

    @Test
    public void twoObjectsWithDifferentIdMustBeDifferent() {
        SampleEntity o1 = new SampleEntity(new Id("id1"));
        SampleEntity o2 = new SampleEntity(new Id("id2"));

        assertThat(o1).isNotEqualTo(o2);
    }



}


class SampleEntity extends BaseEntity {
    public SampleEntity(Id id) {
        super(id);
    }

}