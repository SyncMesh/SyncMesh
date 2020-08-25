// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package org.syncmesh;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer;
import com.apollographql.apollo.api.internal.QueryDocumentMinifier;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller;
import com.apollographql.apollo.api.internal.ResponseReader;
import com.apollographql.apollo.api.internal.ResponseWriter;
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser;
import com.apollographql.apollo.api.internal.Utils;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.syncmesh.type.CustomType;

public final class AllTalksQuery implements Query<AllTalksQuery.Data, AllTalksQuery.Data, Operation.Variables> {
  public static final String OPERATION_ID = "b95fcee923c1cc23d483114979b2d43bb20a5f6118d67139193691bcb091b0f0";

  public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify(
    "query AllTalksQuery {\n"
        + "  allTalks {\n"
        + "    __typename\n"
        + "    id\n"
        + "    title\n"
        + "    description\n"
        + "  }\n"
        + "}"
  );

  public static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "AllTalksQuery";
    }
  };

  private final Operation.Variables variables;

  public AllTalksQuery() {
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
  public AllTalksQuery.Data wrapData(AllTalksQuery.Data data) {
    return data;
  }

  @Override
  public Operation.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<AllTalksQuery.Data> responseFieldMapper() {
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
  public Response<AllTalksQuery.Data> parse(@NotNull final BufferedSource source,
      @NotNull final ScalarTypeAdapters scalarTypeAdapters) throws IOException {
    return SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters);
  }

  @Override
  @NotNull
  public Response<AllTalksQuery.Data> parse(@NotNull final ByteString byteString,
      @NotNull final ScalarTypeAdapters scalarTypeAdapters) throws IOException {
    return parse(new Buffer().write(byteString), scalarTypeAdapters);
  }

  @Override
  @NotNull
  public Response<AllTalksQuery.Data> parse(@NotNull final BufferedSource source) throws
      IOException {
    return parse(source, ScalarTypeAdapters.DEFAULT);
  }

  @Override
  @NotNull
  public Response<AllTalksQuery.Data> parse(@NotNull final ByteString byteString) throws
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

  @Override
  @NotNull
  public ByteString composeRequestBody(final boolean autoPersistQueries,
      final boolean withQueryDocument, @NotNull final ScalarTypeAdapters scalarTypeAdapters) {
    return OperationRequestBodyComposer.compose(this, autoPersistQueries, withQueryDocument, scalarTypeAdapters);
  }

  public static final class Builder {
    Builder() {
    }

    public AllTalksQuery build() {
      return new AllTalksQuery();
    }
  }

  /**
   * Data from the response after executing this GraphQL operation
   */
  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forList("allTalks", "allTalks", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nullable List<AllTalk> allTalks;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Data(@Nullable List<AllTalk> allTalks) {
      this.allTalks = allTalks;
    }

    public @Nullable List<AllTalk> allTalks() {
      return this.allTalks;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeList($responseFields[0], allTalks, new ResponseWriter.ListWriter() {
            @Override
            public void write(List items, ResponseWriter.ListItemWriter listItemWriter) {
              for (Object item : items) {
                listItemWriter.writeObject(((AllTalk) item).marshaller());
              }
            }
          });
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "allTalks=" + allTalks
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
        return ((this.allTalks == null) ? (that.allTalks == null) : this.allTalks.equals(that.allTalks));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (allTalks == null) ? 0 : allTalks.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final AllTalk.Mapper allTalkFieldMapper = new AllTalk.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final List<AllTalk> allTalks = reader.readList($responseFields[0], new ResponseReader.ListReader<AllTalk>() {
          @Override
          public AllTalk read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<AllTalk>() {
              @Override
              public AllTalk read(ResponseReader reader) {
                return allTalkFieldMapper.map(reader);
              }
            });
          }
        });
        return new Data(allTalks);
      }
    }
  }

  public static class AllTalk {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forCustomType("id", "id", null, true, CustomType.ID, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("title", "title", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("description", "description", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @Nullable String id;

    final @NotNull String title;

    final @Nullable String description;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public AllTalk(@NotNull String __typename, @Nullable String id, @NotNull String title,
        @Nullable String description) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.id = id;
      this.title = Utils.checkNotNull(title, "title == null");
      this.description = description;
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    public @Nullable String id() {
      return this.id;
    }

    public @NotNull String title() {
      return this.title;
    }

    public @Nullable String description() {
      return this.description;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeCustom((ResponseField.CustomTypeField) $responseFields[1], id);
          writer.writeString($responseFields[2], title);
          writer.writeString($responseFields[3], description);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AllTalk{"
          + "__typename=" + __typename + ", "
          + "id=" + id + ", "
          + "title=" + title + ", "
          + "description=" + description
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AllTalk) {
        AllTalk that = (AllTalk) o;
        return this.__typename.equals(that.__typename)
         && ((this.id == null) ? (that.id == null) : this.id.equals(that.id))
         && this.title.equals(that.title)
         && ((this.description == null) ? (that.description == null) : this.description.equals(that.description));
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
        h ^= (id == null) ? 0 : id.hashCode();
        h *= 1000003;
        h ^= title.hashCode();
        h *= 1000003;
        h ^= (description == null) ? 0 : description.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<AllTalk> {
      @Override
      public AllTalk map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String id = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[1]);
        final String title = reader.readString($responseFields[2]);
        final String description = reader.readString($responseFields[3]);
        return new AllTalk(__typename, id, title, description);
      }
    }
  }
}
