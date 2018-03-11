
## FbComments
###### by Sebastian Cipolat
[@seba_cipolat](http://twitter.com/seba_cipolat)

[www.sebastiancipolat.com](www.sebastiancipolat.com)

[sebastiancipolat Linkedin](www.linkedin.com/in/sebastiancipolat)

A simple way to show embebed facebook comments social plugin into a Android app.
This View is based on WebView.
Facebook don't provide any native way to get the comments from a commentary box.



**Usage**


 `<com.cipolat.FbCommentsView.FbCommentsView`
        `android:id="@+id/commentsView"`
        `android:layout_width="match_parent"`
        `android:layout_height="match_parent"`
        `app:layout_behavior="@string/appbar_scrolling_view_behavior"`
 `/>`
 
 You need to set an targer url and Facebook AppID
 
 `FbCommentsView commentsView =findByViewID(R.id.comeNts_view)
  String postUrl= URL TARGET
  String fbAppID=FB APP ID

  FbConfig config=new FbConfig(fbAppID,postUrl);
  commentsView.loadData(config);`
  
  use .loadData and pass FbConfig to load the comments
 
 See also
 [https://developers.facebook.com/docs/plugins/comments/] https://developers.facebook.com/docs/plugins/comments/
 
 Callback 
 
 use setListener to receive the callback when the load fails or load ok.
 

**Load**

<img src='https://github.com/sebacipolat/FbComments/blob/master/load.png' width="250"/>


**Commentaries loaded**

<img src='https://github.com/sebacipolat/FbComments/blob/master/comments.png' width="250"/>

Demostration purpose only, not a completed app.

Demostration purpose only, not a completed app.





  
**FeedBack**

  Please feel free to report bugs, suggestion,etc. I'll be pending.
  
  If you will made a fork an use on your project let me know! to add to the description.

[@seba_cipolat](http://twitter.com/seba_cipolat)

[www.sebastiancipolat.com](www.sebastiancipolat.com)

[sebastiancipolat Linkedin](www.linkedin.com/in/sebastiancipolat)

## License
    Copyright 2018 sebastian cipolat

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
