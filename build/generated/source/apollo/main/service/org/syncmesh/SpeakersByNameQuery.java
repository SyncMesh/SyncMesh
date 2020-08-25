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
import com.apollographql.apollo.api.internal.InputFieldMarshaller;
import com.apollographql.apollo.api.internal.InputFieldWriter;
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
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.syncmesh.type.CustomType;

public final class SpeakersByNameQuery implements Query<SpeakersByNameQuery.Data, SpeakersByNameQuery.Data, SpeakersByNameQuery.Variables> {
  public static final String OPERATION_ID = "99783ecb1561f503dfef2745160215cb5d177ce5d3d02b58202fcd7ed3dcd3e1";

  public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify(
    "query SpeakersByName($name: String!) {\n"
        + "  speakersByName(name: $name) {\n"
        + "    __typename\n"
        + "    id\n"
        + "    name\n"
        + "    twitter\n"
        + "  }\n"
        + "}"
  );

  public static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "SpeakersByName";
    }
  };

  private final SpeakersByNameQuery.Variables variables;

  public SpeakersByNameQuery(@NotNull String name) {
    Utils.checkNotNull(name, "name == null");
    variables = new SpeakersByNameQuery.Variables(name);
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
  public SpeakersByNameQuery.Data wrapData(SpeakersByNameQuery.Data data) {
    return data;
  }

  @Override
  public SpeakersByNameQuery.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<SpeakersByNameQuery.Data> responseFieldMapper() {
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
  public Response<SpeakersByNameQuery.Data> parse(@NotNull final BufferedSource source,
      @NotNull final ScalarTypeAdapters scalarTypeAdapters) throws IOException {
    return SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters);
  }

  @Override
  @NotNull
  public Response<SpeakersByNameQuery.Data> parse(@NotNull final ByteString byteString,
      @NotNull final ScalarTypeAdapters scalarTypeAdapters) throws IOException {
    return parse(new Buffer().write(byteString), scalarTypeAdapters);
  }

  @Override
  @NotNull
  public Response<SpeakersByNameQuery.Data> parse(@NotNull final BufferedSource source) throws
      IOException {
    return parse(source, ScalarTypeAdapters.DEFAULT);
  }

  @Override
  @NotNull
  public Response<SpeakersByNameQuery.Data> parse(@NotNull final ByteString byteString) throws
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
    private @NotNull String name;

    Builder() {
    }

    public Builder name(@NotNull String name) {
      this.name = name;
      return this;
    }

    public SpeakersByNameQuery build() {
      Utils.checkNotNull(name, "name == null");
      return new SpeakersByNameQuery(name);
    }
  }

  public static final class Variables extends Operation.Variables {
    private final @NotNull String name;

    private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(@NotNull String name) {
      this.name = name;
      this.valueMap.put("name", name);
    }

    public @NotNull String name() {
      return name;
    }

    @Override
    public Map<String, Object> valueMap() {
      return Collections.unmodifiableMap(valueMap);
    }

    @Override
    public InputFieldMarshaller marshaller() {
      return new InputFieldMarshaller() {
        @Override
        public void marshal(InputFieldWriter writer) throws IOException {
          writer.writeString("name", name);
        }
      };
    }
  }

  /**
   * Data from the response after executing this GraphQL operation
   */
  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forList("speakersByName", "speakersByName", new UnmodifiableMapBuilder<String, Object>(1)
      .put("name", new UnmodifiableMapBuilder<String, Object>(2)
        .put("kind", "Variable")
        .put("variableName", "name")
        .build())
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nullable List<SpeakersByName> speakersByName;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Data(@Nullable List<SpeakersByName> speakersByName) {
      this.speakersByName = speakersByName;
    }

    public @Nullable List<SpeakersByName> speakersByName() {
      return this.speakersByName;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeList($responseFields[0], speakersByName, new ResponseWriter.ListWriter() {
            @Override
            public void write(List items, ResponseWriter.ListItemWriter listItemWriter) {
              for (Object item : items) {
                listItemWriter.writeObject(((SpeakersByName) item).marshaller());
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
          + "speakersByName=" + speakersByName
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
        return ((this.speakersByName == null) ? (that.speakersByName == null) : this.speakersByName.equals(that.speakersByName));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (speakersByName == null) ? 0 : speakersByName.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final SpeakersByName.Mapper speakersByNameFieldMapper = new SpeakersByName.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final List<SpeakersByName> speakersByName = reader.readList($responseFields[0], new ResponseReader.ListReader<SpeakersByName>() {
          @Override
          public SpeakersByName read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<SpeakersByName>() {
              @Override
              public SpeakersByName read(ResponseReader reader) {
                return speakersByNameFieldMapper.map(reader);
              }
            });
          }
        });
        return new Data(speakersByName);
      }
    }
  }

  public static class SpeakersByName {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forCustomType("id", "id", null, true, CustomType.ID, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("twitter", "twitter", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @Nullable String id;

    final @NotNull String name;

    final @Nullable String twitter;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public SpeakersByName(@NotNull String __typename, @Nullable String id, @NotNull String name,
        @Nullable String twitter) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.id = id;
      this.name = Utils.checkNotNull(name, "name == null");
      this.twitter = twitter;
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    public @Nullable String id() {
      return this.id;
    }

    public @NotNull String name() {
      return this.name;
    }

    public @Nullable String twitter() {
      return this.twitter;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeCustom((ResponseField.CustomTypeField) $responseFields[1], id);
          writer.writeString($responseFields[2], name);
          writer.writeString($responseFields[3], twitter);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "SpeakersByName{"
          + "__typename=" + __typename + ", "
          + "id=" + id + ", "
          + "name=" + name + ", "
          + "twitter=" + twitter
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof SpeakersByName) {
        SpeakersByName that = (SpeakersByName) o;
        return this.__typename.equals(that.__typename)
         && ((this.id == null) ? (that.id == null) : this.id.equals(that.id))
         && this.name.equals(that.name)
         && ((this.twitter == null) ? (that.twitter == null) : this.twitter.equals(that.twitter));
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
        h ^= name.hashCode();
        h *= 1000003;
        h ^= (twitter == null) ? 0 : twitter.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<SpeakersByName> {
      @Override
      public SpeakersByName map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String id = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[1]);
        final String name = reader.readString($responseFields[2]);
        final String twitter = reader.readString($responseFields[3]);
        return new SpeakersByName(__typename, id, name, twitter);
      }
    }
  }
}
