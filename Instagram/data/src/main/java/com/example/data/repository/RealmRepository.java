package com.example.data.repository;

/**
 * Created by phundal2091 on 9/4/17.
 */
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmRepository<T extends RealmObject> {

    private Realm realm;

    public RealmRepository(Realm realm){
        this.realm = realm;
    }

    public T create(final Class<? extends RealmObject> clazz){
        realm.beginTransaction();
        T realmObject = (T)realm.createObject(clazz);
        realm.commitTransaction();

        return realmObject;
    }

    public T save(final T model, final IEditDelegate<T> editDelegate) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if(editDelegate != null) {
                    editDelegate.call(model);
                }
                realm.copyToRealmOrUpdate(model);
            }
        });

        return model;
    }

    public List<T> save(final List<T> models, final IEditDelegate<T> editDelegate) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if(editDelegate != null) {
                    for (T model :
                            models) {
                        editDelegate.call(model);
                    }
                }
                realm.copyToRealmOrUpdate(models);
            }
        });

        return models;
    }

    public T getFirst(Class<? extends RealmObject> clazz) {

        if(realm.where(clazz).findAll().size() > 0) {
            return (T) realm.where(clazz).findAll().first();
        }
        return null;
    }

    public <T extends RealmObject> void delete(final T model) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm();
            }
        });
    }

    public RealmResults<?> get(Class<? extends RealmObject> clazz) {
        return realm.where(clazz).findAll();
    }

    public RealmResults<?> get(Class<? extends RealmObject> clazz, String orderFieldName, Sort sort) {
        return realm.where(clazz).findAllSorted(orderFieldName, sort);
    }

    public RealmQuery<?> query(Class<? extends RealmObject> clazz) {
        return realm.where(clazz);
    }

    public RealmResults<?> getByIds(Class<? extends  RealmObject> clazz, String idFieldName,long[] ids) {
        RealmQuery<? extends RealmObject> query = realm.where(clazz);
        int i = 0;
        for (long id : ids) {
            // The or() operator requires left hand and right hand elements. If articleIds had
            // only one element then it would crash ("Missing right-hand side of OR")
            if (i++ > 0) {
                query = query.or();
            }
            query = query.equalTo(idFieldName, id);
        }
        return query.findAll().sort(idFieldName, Sort.DESCENDING);
    }

    public void deleteAll(final Class<? extends RealmObject> clazz) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(clazz);
            }
        });
    }

    public interface IEditDelegate<T extends RealmObject> {
        void call(T model);
    }

    public T getFirstById(Class<? extends RealmObject> clazz, String id) {

        if(realm.where(clazz).findAll().size() > 0) {
            return (T) realm.where(clazz).equalTo("id", id).findFirst();
        }
        return null;
    }

    public T getFirstById(Class<? extends RealmObject> clazz, long id) {

        if(realm.where(clazz).equalTo("id", id).findAll().size() > 0) {
            return (T) realm.where(clazz).equalTo("id", id).findFirst();
        }
        return null;
    }

    public T getFirstById(Class<? extends RealmObject> clazz, String idColumnName, long id) {

        if(realm.where(clazz).equalTo(idColumnName, id).findAll().size() > 0) {
            return (T) realm.where(clazz).equalTo(idColumnName, id).findFirst();
        }
        return null;
    }
}