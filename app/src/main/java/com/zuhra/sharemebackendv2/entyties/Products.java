package com.zuhra.sharemebackendv2.entyties;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class Products
{
  private java.util.Date release_date;
  private String ownerId;
  private String serial_no;
  private String objectId;
  private String name;
  private java.util.Date updated;
  private java.util.Date created;
  private String code;
  private String note;
  private java.util.Date add_date;
  private java.util.List<Categories> category_id;
  private java.util.List<Orgs> org_id;
  private java.util.List<Brands> brand_id;
  public java.util.Date getRelease_date()
  {
    return release_date;
  }

  public void setRelease_date( java.util.Date release_date )
  {
    this.release_date = release_date;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getSerial_no()
  {
    return serial_no;
  }

  public void setSerial_no( String serial_no )
  {
    this.serial_no = serial_no;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getCode()
  {
    return code;
  }

  public void setCode( String code )
  {
    this.code = code;
  }

  public String getNote()
  {
    return note;
  }

  public void setNote( String note )
  {
    this.note = note;
  }

  public java.util.Date getAdd_date()
  {
    return add_date;
  }

  public void setAdd_date( java.util.Date add_date )
  {
    this.add_date = add_date;
  }

  public java.util.List<Categories> getCategory_id()
  {
    return category_id;
  }

  public void setCategory_id( java.util.List<Categories> category_id )
  {
    this.category_id = category_id;
  }

  public java.util.List<Orgs> getOrg_id()
  {
    return org_id;
  }

  public void setOrg_id( java.util.List<Orgs> org_id )
  {
    this.org_id = org_id;
  }

  public java.util.List<Brands> getBrand_id()
  {
    return brand_id;
  }

  public void setBrand_id( java.util.List<Brands> brand_id )
  {
    this.brand_id = brand_id;
  }

                                                    
  public Products save()
  {
    return Backendless.Data.of( Products.class ).save( this );
  }

  public Future<Products> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Products> future = new Future<Products>();
      Backendless.Data.of( Products.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Products> callback )
  {
    Backendless.Data.of( Products.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Products.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( Products.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Products.class ).remove( this, callback );
  }

  public static Products findById( String id )
  {
    return Backendless.Data.of( Products.class ).findById( id );
  }

  public static Future<Products> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Products> future = new Future<Products>();
      Backendless.Data.of( Products.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Products> callback )
  {
    Backendless.Data.of( Products.class ).findById( id, callback );
  }

  public static Products findFirst()
  {
    return Backendless.Data.of( Products.class ).findFirst();
  }

  public static Future<Products> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Products> future = new Future<Products>();
      Backendless.Data.of( Products.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Products> callback )
  {
    Backendless.Data.of( Products.class ).findFirst( callback );
  }

  public static Products findLast()
  {
    return Backendless.Data.of( Products.class ).findLast();
  }

  public static Future<Products> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Products> future = new Future<Products>();
      Backendless.Data.of( Products.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Products> callback )
  {
    Backendless.Data.of( Products.class ).findLast( callback );
  }

  public static BackendlessCollection<Products> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Products.class ).find( query );
  }

  public static Future<BackendlessCollection<Products>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Products>> future = new Future<BackendlessCollection<Products>>();
      Backendless.Data.of( Products.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Products>> callback )
  {
    Backendless.Data.of( Products.class ).find( query, callback );
  }
}