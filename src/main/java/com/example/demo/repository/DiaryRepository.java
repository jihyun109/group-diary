//package com.example.demo.repository;
//
//import com.example.demo.entity.DiaryEntity;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.FluentQuery;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//@Repository
//public class DiaryRepositoryextends extends JpaRepository<DiaryEntity, Long> {
//
//    @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public <S extends DiaryEntity> S saveAndFlush(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends DiaryEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public void deleteAllInBatch(Iterable<DiaryEntity> entities) {
//
//    }
//
//    @Override
//    public void deleteAllByIdInBatch(Iterable<Long> longs) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//    @Override
//    public DiaryEntity getOne(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public DiaryEntity getById(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public DiaryEntity getReferenceById(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public <S extends DiaryEntity> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends DiaryEntity> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends DiaryEntity> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends DiaryEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends DiaryEntity> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends DiaryEntity> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public <S extends DiaryEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
//
//    @Override
//    public <S extends DiaryEntity> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends DiaryEntity> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<DiaryEntity> findById(Long aLong) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return false;
//    }
//
//    @Override
//    public List<DiaryEntity> findAll() {
//        return null;
//    }
//
//    @Override
//    public List<DiaryEntity> findAllById(Iterable<Long> longs) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//
//    }
//
//    @Override
//    public void delete(DiaryEntity entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends Long> longs) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends DiaryEntity> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public List<DiaryEntity> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<DiaryEntity> findAll(Pageable pageable) {
//        return null;
//    }
//}
