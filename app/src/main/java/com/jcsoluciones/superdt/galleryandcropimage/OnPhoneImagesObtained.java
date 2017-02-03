package com.jcsoluciones.superdt.galleryandcropimage;

import java.util.Vector;

/**
 * Created by ADMIN on 02/02/2017.
 */
public interface OnPhoneImagesObtained {
    void onComplete( Vector<PhoneAlbum> albums );
    void onError();
}
