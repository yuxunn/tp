package seedu.address.storage;

import static seedu.address.model.person.Client.TYPE_CLIENT;
import static seedu.address.model.person.Lead.TYPE_LEAD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
public class JsonAdaptedLead{
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lead's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String type;
    private final String address;
    private final String keyMilestone;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedLead} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedLead(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("type") String type,
                             @JsonProperty("address") String address,
                             @JsonProperty("key milestone") String keyMilestone,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.address = address;
        this.keyMilestone = keyMilestone;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Lead} into this class for Jackson use.
     */
    public JsonAdaptedLead(Lead source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        type = source.getType().value;
        keyMilestone = source.getKeyMilestone().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Lead} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Lead toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        if (!KeyMilestone.isValidKeyMilestone(keyMilestone)) {
            throw new IllegalValueException(KeyMilestone.MESSAGE_CONSTRAINTS);
        }
        final KeyMilestone modelKeyMilestone = new KeyMilestone(keyMilestone);
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Type"));
        }
        if (type.equals(TYPE_LEAD)) {
            return new Lead(modelName, modelPhone, modelEmail, modelAddress, modelKeyMilestone, modelTags);
        } else {
            throw new IllegalValueException(Type.MESSAGE_CONSTRAINTS);
        }
    }
}
