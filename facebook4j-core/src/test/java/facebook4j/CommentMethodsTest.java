/*
 * Copyright 2012 Ryuji Yamashita
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package facebook4j;

import facebook4j.internal.http.RequestMethod;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class CommentMethodsTest {

    public static class getComment extends MockFacebookTestBase {
        @Test
        public void simple() throws Exception {
            facebook.setMockJSON("mock_json/comment/simple.json");
            Comment actual = facebook.getComment("100000000000001_50000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_50000001")));

            assertThat(actual.isUserLikes(), is(false));
            assertThat(actual.getMessage(), is("Enjoy!!"));
            assertThat(actual.getId(), is("100000000000001_50000001"));
            assertThat(actual.getLikeCount(), is(1));
            assertThat(actual.getFrom().getId(), is("1234567890123456"));
            assertThat(actual.getFrom().getName(), is("Name Name1"));
            assertThat(actual.canRemove(), is(false));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2013-08-07T04:08:42+0000")));
        }
    }

/*
    @Test
    public void get() throws Exception {
        ResponseList<Album> albums = facebook1.getAlbums();
        ResponseList<Comment> albumComments = facebook1.getAlbumComments(albums.get(0).getId());
        
        for (Comment comment : albumComments) {
            Comment actual = facebook1.getComment(comment.getId());
            assertThat(actual.getMessage(), is(comment.getMessage()));
        }
        
    }

    @Test
    public void delete() throws Exception {
        ResponseList<Album> albums = facebook1.getAlbums();
        String albumId = albums.get(0).getId();
        String commentId = facebook1.commentAlbum(albumId, "Comment on an Album");
        
        boolean deleteResult = facebook1.deleteComment(commentId);
        assertThat(deleteResult, is(true));
    }

    @Test
    public void like() throws Exception {
        ResponseList<Album> albums = facebook1.getAlbums();
        String albumId = albums.get(0).getId();
        String commentId = facebook1.commentAlbum(albumId, "Comment on an Album");
        
        boolean likeResult = facebook1.likeComment(commentId);
        assertThat(likeResult, is(true));

        ResponseList<Like> commentLikes = facebook1.getCommentLikes(commentId);
        assertThat(commentLikes.size(), is(1));
        
        boolean unlikeResult = facebook1.unlikeComment(commentId);
        assertThat(unlikeResult, is(true));
        
        commentLikes = facebook1.getCommentLikes(commentId);
        assertThat(commentLikes.size(), is(0));

        facebook1.deleteComment(commentId);
    }
*/

}
