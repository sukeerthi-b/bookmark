package com.bookmark.domain.helper;

import com.bookmark.domain.exception.BookmarkException;
import com.bookmark.domain.model.Group;
import com.bookmark.domain.port.ObtainGroup;

import java.util.List;

import static com.bookmark.domain.common.BookmarkConstants.PORTS_NOT_AVAILABLE;
import static java.util.Objects.isNull;

public enum GroupHelper {
    GROUP_HELPER;

    private ObtainGroup obtainGroup;

    public void init(final ObtainGroup obtainGroup) {
        this.obtainGroup = obtainGroup;
    }

    public List<Group> getGroups() {
        if(isAllPortsNotAvailable()) {
            throw new BookmarkException(PORTS_NOT_AVAILABLE);
        }
        return obtainGroup.getGroups();
    }

    public void save(final Group group) {
        if(isAllPortsNotAvailable() || isNull(group)) {
            throw new BookmarkException(PORTS_NOT_AVAILABLE);
        }
        obtainGroup.save(group);
    }

    private boolean isAllPortsNotAvailable() {
        return isNull(this.obtainGroup);
    }
}
