package com.ecommerce.userservice.repository;

import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.model.UserStatus;
import com.ecommerce.userservice.model.Users;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserSpecificationsTest {

    @SuppressWarnings("unchecked")
    private final Root<Users> root = mock(Root.class);
    private final CriteriaQuery<?> query = mock(CriteriaQuery.class);
    private final CriteriaBuilder cb = mock(CriteriaBuilder.class);

    @Test
    @SuppressWarnings("unchecked")
    void hasRoleFiltraPorRolYDevuelveNullSiNoSeIndica() {
        Path<Object> rolePath = mock(Path.class);
        Predicate predicate = mock(Predicate.class);
        when(root.get("role")).thenReturn(rolePath);
        when(cb.equal(rolePath, Role.ADMINISTRADOR)).thenReturn(predicate);

        assertNull(UserSpecifications.hasRole(null).toPredicate(root, query, cb));
        assertNotNull(UserSpecifications.hasRole(Role.ADMINISTRADOR).toPredicate(root, query, cb));
    }

    @Test
    @SuppressWarnings("unchecked")
    void hasStatusFiltraPorEstadoActivoYDevuelveNullSiNoSeIndica() {
        Path<Object> activePath = mock(Path.class);
        Predicate predicate = mock(Predicate.class);
        when(root.<Object>get("isActive")).thenReturn(activePath);
        when(cb.equal(activePath, true)).thenReturn(predicate);
        when(cb.equal(activePath, false)).thenReturn(predicate);

        assertNull(UserSpecifications.hasStatus(null).toPredicate(root, query, cb));
        assertNotNull(UserSpecifications.hasStatus(UserStatus.ACTIVE).toPredicate(root, query, cb));
        assertNotNull(UserSpecifications.hasStatus(UserStatus.INACTIVE).toPredicate(root, query, cb));
    }

    @Test
    @SuppressWarnings("unchecked")
    void matchesSearchFiltraPorVariosCamposYDevuelveNullSiNoHayTexto() {
        Path<String> namePath = mock(Path.class);
        Expression<String> lowerName = mock(Expression.class);
        Predicate predicate = mock(Predicate.class);
        when(root.<String>get("name")).thenReturn(namePath);
        when(root.<String>get("paternalName")).thenReturn(namePath);
        when(root.<String>get("maternalName")).thenReturn(namePath);
        when(root.<String>get("email")).thenReturn(namePath);
        when(cb.lower(any())).thenReturn(lowerName);
        when(cb.like(eq(lowerName), any(String.class))).thenReturn(predicate);
        when(cb.or(any(Predicate.class), any(Predicate.class), any(Predicate.class), any(Predicate.class)))
                .thenReturn(predicate);

        Specification<Users> specification = UserSpecifications.matchesSearch(" Isaac ");

        assertNull(UserSpecifications.matchesSearch(null).toPredicate(root, query, cb));
        assertNull(UserSpecifications.matchesSearch("   ").toPredicate(root, query, cb));
        assertNotNull(specification.toPredicate(root, query, cb));
    }
}
