// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package org.syncmesh;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.Subscription;
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer;
import com.apollographql.apollo.api.internal.QueryDocumentMinifier;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller;
import com.apollographql.apollo.api.internal.ResponseReader;
import com.apollographql.apollo.api.internal.ResponseWriter;
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TalkScoresSubscription implements Subscription<TalkScoresSubscription.Data, TalkScoresSubscription.Data, Operation.Variables> {
  public static final String OPERATION_ID = "a86fdc6355627ae7a76a5948260feff47725bc9118bb3aa8426087f7659b9063";

  public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify(
    "subscription TalkScoresSubscription {\n"
        + "  talkScores(title: \"great talk\") {\n"
        + "    __typename\n"
        + "    title\n"
        + "    score\n"
        + "  }\n"
        + "}"
  );

  public static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "TalkScoresSubscription";
    }
  };

  private final Operation.Variables variables;

  public TalkScoresSubscription() {
    this.variables = Operation.EMPTY_VARIABLES;
  }

  @Override
  public String operationId() {
    return OPERATION_ID;
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public TalkScoresSubscription.Data wrapData(TalkScoresSubscription.Data data) {
    return data;
  }

  @Override
  public Operation.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<TalkScoresSubscription.Data> responseFieldMapper() {
    return new Data.Mapper();
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public OperationName name() {
    return OPERATION_NAME;
  }

  @Override
  @NotNull
  public Response<TalkScoresSubscription.Data> parse(@NotNull final BufferedSource source,
      @NotNull final ScalarTypeAdapters scalarTypeAdapters) throws IOException {
    return SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters);
  }

  @Override
  @NotNull
  public Response<TalkScoresSubscription.Data> parse(@NotNull final ByteString byteString,
      @NotNull final ScalarTypeAdapters scalarTypeAdapters) throws IOException {
    return parse(new Buffer().write(byteString), scalarTypeAdapters);
  }

  @Override
  @NotNull
  public Response<TalkScoresSubscription.Data> parse(@NotNull final BufferedSource source) throws
      IOException {
    return parse(source, ScalarTypeAdapters.DEFAULT);
  }

  @Override
  @NotNull
  public Response<TalkScoresSubscription.Data> parse(@NotNull final ByteString byteString) throws
      IOException {
    return parse(byteString, ScalarTypeAdapters.DEFAULT);
  }

  @Override
  @NotNull
  public ByteString composeRequestBody(@NotNull final ScalarTypeAdapters scalarTypeAdapters) {
    return OperationRequestBodyComposer.compose(this, false, true, scalarTypeAdapters);
  }

  @NotNull
  @Override
  public ByteString composeRequestBody() {
    return OperationRequestBodyComposer.compose(this, false, true, ScalarTypeAdapters.DEFAULT);
  }

  public static final class Builder {
    Builder() {
    }

    public TalkScoresSubscription build() {
      return new TalkScoresSubscription();
    }
  }

  /**
   * Data from the response after executing this GraphQL operation
   */
  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forObject("talkScores", "talkScores", new UnmodifiableMapBuilder<String, Object>(1)
      .put("title", "great talk")
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nullable TalkScores talkScores;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Data(@Nullable TalkScores talkScores) {
      this.talkScores = talkScores;
    }

    public @Nullable TalkScores talkScores() {
      return this.talkScores;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeObject($responseFields[0], talkScores != null ? talkScores.marshaller() : null);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "talkScores=" + talkScores
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return ((this.talkScores == null) ? (that.talkScores == null) : this.talkScores.equals(that.talkScores));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (talkScores == null) ? 0 : talkScores.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final TalkScores.Mapper talkScoresFieldMapper = new TalkScores.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final TalkScores talkScores = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<TalkScores>() {
          @Override
          public TalkScores read(ResponseReader reader) {
            return talkScoresFieldMapper.map(reader);
          }
        });
        return new Data(talkScores);
      }
    }
  }

  public static class TalkScores {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("title", "title", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forInt("score", "score", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @NotNull String title;

    final @Nullable Integer score;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public TalkScores(@NotNull String __typename, @NotNull String title, @Nullable Integer score) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.title = Utils.checkNotNull(title, "title == null");
      this.score = score;
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    public @NotNull String title() {
      return this.title;
    }

    public @Nullable Integer score() {
      return this.score;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], title);
          writer.writeInt($responseFields[2], score);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "TalkScores{"
          + "__typename=" + __typename + ", "
          + "title=" + title + ", "
          + "score=" + score
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof TalkScores) {
        TalkScores that = (TalkScores) o;
        return this.__typename.equals(that.__typename)
         && this.title.equals(that.title)
         && ((this.score == null) ? (that.score == null) : this.score.equals(that.score));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= title.hashCode();
        h *= 1000003;
        h ^= (score == null) ? 0 : score.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<TalkScores> {
      @Override
      public TalkScores map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String title = reader.readString($responseFields[1]);
        final Integer score = reader.readInt($responseFields[2]);
        return new TalkScores(__typename, title, score);
      }
    }
  }
}
